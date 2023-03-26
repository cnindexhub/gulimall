package com.atguigu.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author DengPengFei
 * @email DengTPengTFei@163.com
 * @date 2023-02-15 22:32:53
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 获取商品三级分类并构成父子关系数据结构
     * @return
     */
    List<CategoryEntity> listWithTree();

    /**
     * 批量删除菜单
     * @param ids 菜单编号列表
     */
    void removeMenusByIds(List<Long> ids);

    List<Long> findCatelogPath(Long attrGroupId);

    void updateDetail(CategoryEntity category);
}

