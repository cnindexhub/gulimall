package com.atguigu.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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