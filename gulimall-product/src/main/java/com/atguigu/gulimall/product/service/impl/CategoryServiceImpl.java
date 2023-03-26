package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.dao.CategoryBrandRelationDao;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.xml.ws.soap.Addressing;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 查出所有分类
        List<CategoryEntity> all = baseMapper.selectList(null);
        List<CategoryEntity> list = all.stream().filter(category -> {
            return category.getCatLevel() == 1;
        }).map((category) -> {
            category.setChildren(getChildren(category, all));
            return category;
        }).sorted((item1, item2) -> {
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public void removeMenusByIds(List<Long> ids) {
        // TODO 删除之前需要做的业务逻辑
        baseMapper.deleteBatchIds(ids);
    }

    /**
     * 获取一个分类编号以及其所有的父分类编号以从上到下的顺序组成的数组
     * @param catelogId 分类编号
     * @return 一个分类编号以及其所有的父分类编号以从上到下的顺序组成的数组
     */
    @Override
    public List<Long> findCatelogPath(Long catelogId) {
        List<Long> catelogPath = new ArrayList<>();
        findCatelogPath(catelogPath, catelogId);
        Collections.reverse(catelogPath);
        return catelogPath;
    }


    /**
     * 修改分类信息 及其相关信息处理
     * @param category 分类信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDetail(CategoryEntity category) {
        // 修改分类信息
        updateById(category);
        String categoryName = category.getName();
        if (!StringUtils.hasText(categoryName)) {
            return;
        }
        // 填充品牌和分类关联信息
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setCatelogName(categoryName);
        categoryBrandRelationEntity.setCatelogId(category.getCatId());
        // 条件为当前修改的分类编号
        UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("catelog_id", category.getCatId());
        // 修改品牌和分类关联信息
        categoryBrandRelationDao.update(categoryBrandRelationEntity, updateWrapper);

        // TODO 其他相关操作
    }


    /**
     * 递归查找一个分类编号以及其所有的父分类编号以从上到下的顺序组成的数组
     * @param catelogPath 一个分类编号以及其所有的父分类编号以从上到下的顺序组成的数组
     * @param catelogId 分类编号
     */
    public void findCatelogPath(List<Long> catelogPath, Long catelogId) {
        catelogPath.add(catelogId);
        CategoryEntity categoryEntity = baseMapper.selectById(catelogId);
        if (categoryEntity.getParentCid() != 0) {
            findCatelogPath(catelogPath, categoryEntity.getParentCid());
        }
    }


    /**
     * @param cur 当前父级分类
     * @param all 所有分类
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity cur, List<CategoryEntity> all) {
        List<CategoryEntity> curLevelCategoryList = all.stream().filter(category -> category.getCatLevel() == cur.getCatLevel() + 1 && category.getParentCid() == cur.getCatId()).collect(Collectors.toList());
        // 没有下一级则直接返回空数组
        if (curLevelCategoryList.size() == 0) {
            return new ArrayList<>();
        }
        return curLevelCategoryList.stream().map(category -> {
            category.setChildren(getChildren(category, all));
            return category;
        }).sorted((item1, item2) -> {
            return (item1.getSort() == null ? 0 : item1.getSort()) - (item2.getSort() == null ? 0 : item2.getSort());
        }).collect(Collectors.toList());
    }

}