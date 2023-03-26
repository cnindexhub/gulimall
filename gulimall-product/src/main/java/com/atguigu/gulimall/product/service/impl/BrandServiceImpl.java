package com.atguigu.gulimall.product.service.impl;

import com.atguigu.gulimall.product.dao.CategoryBrandRelationDao;
import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.BrandDao;
import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationDao categoryBrandRelationDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.hasText(key)) {
            queryWrapper.like("brand_id", key).or().like("name", key);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * 修改品牌信息 及其相关信息处理
     * @param brand 品牌信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDetail(BrandEntity brand) {
        // 修改品牌信息
        baseMapper.updateById(brand);
        String brandName = brand.getName();
        if (!StringUtils.hasText(brandName)) {
            return;
        }
        // 填充品牌和分类关联信息
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandName(brandName);
        categoryBrandRelationEntity.setBrandId(brand.getBrandId());
        // 条件为当前修改的品牌编号
        UpdateWrapper<CategoryBrandRelationEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("brand_id", brand.getBrandId());
        // 修改品牌和分类关联信息
        categoryBrandRelationDao.update(categoryBrandRelationEntity, updateWrapper);

        // TODO 其他相关操作
    }

}