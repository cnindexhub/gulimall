package com.atguigu.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.R;



/**
 * 商品三级分类
 *
 * @author DengPengFei
 * @email DengTPengTFei@163.com
 * @date 2023-02-15 22:32:53
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 获取商品三级分类，并返回父子关系数据结构
     */
    @RequestMapping("/list/tree")
    // @RequiresPermissions("product:category:list")
    public R list(@RequestParam Map<String, Object> params){
        List<CategoryEntity> categoryEntities = categoryService.listWithTree();
        return R.ok().put("data", categoryEntities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    // @RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);
        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateDetail(category);

        return R.ok();
    }

    /**
     * 批量删除菜单
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
        categoryService.removeMenusByIds(Arrays.asList(catIds));
        return R.ok();
    }

}
