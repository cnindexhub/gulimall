package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.exception.RRException;
import com.atguigu.gulimall.product.dao.BrandDao;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryBrandRelationDao;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private BrandDao brandDao;



    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保存品牌和分类关系记录
     * @param categoryBrandRelation 品牌和分类关系记录信息
     */
    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        // 根据品牌编号查询品牌信息
        BrandEntity brand = brandDao.selectById(categoryBrandRelation.getBrandId());
        String brandName = brand.getName();
        if (StringUtils.isBlank(brandName)) {
            throw new RRException("当前品牌不存在！");
        }
        // 根据分类编号查询分类信息
        CategoryEntity category = categoryDao.selectById(categoryBrandRelation.getCatelogId());
        String categoryName = category.getName();
        if (StringUtils.isBlank(categoryName)) {
            throw new RRException("当前分类不存在！");
        }
        // 填充品牌名称
        categoryBrandRelation.setBrandName(brandName);
        // 填充分类名称
        categoryBrandRelation.setCatelogName(categoryName);
        // 保存品牌和分类关系记录
        this.baseMapper.insert(categoryBrandRelation);
    }

    /**
     * 判断当前品牌和分类关系记录信息是否已经存在
     * @param categoryBrandRelation 品牌和分类关系记录信息
     * @return { true 存在 } or { false 不存在 }
     */
    @Override
    public boolean exist(CategoryBrandRelationEntity categoryBrandRelation) {
        QueryWrapper<CategoryBrandRelationEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("brand_id", categoryBrandRelation.getBrandId());
        queryWrapper.eq("catelog_id", categoryBrandRelation.getCatelogId());
        return baseMapper.selectCount(queryWrapper) > 0;
    }

}