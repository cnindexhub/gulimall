package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.CategoryBrandRelationEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author DengPengFei
 * @Decription 品牌和分类关联信息逻辑层单元测试类
 * @Email DengTPengTFei@163.com
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryBrandRelationServiceTest {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 测试 保存品牌和分类关系记录
     */
    @Test
    public void testSaveDetail() {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(1L);
        categoryBrandRelationEntity.setCatelogId(225L);
        log.info("入参：{}", categoryBrandRelationEntity);
        this.categoryBrandRelationService.saveDetail(categoryBrandRelationEntity);
        log.info("保存成功！");
    }

    /**
     * 测试 判断当前品牌和分类关系记录信息是否已经存在
     */
    @Test
    public void testExist() {
        CategoryBrandRelationEntity categoryBrandRelationEntity = new CategoryBrandRelationEntity();
        categoryBrandRelationEntity.setBrandId(1L);
        categoryBrandRelationEntity.setCatelogId(225L);
        log.info("入参：{}", categoryBrandRelationEntity);
        boolean exist = this.categoryBrandRelationService.exist(categoryBrandRelationEntity);
        if (exist) {
            log.info("{} ：该品牌和分类关联记录信息已经存在！");
        } else {
            log.info("{} ：该品牌和分类关联记录信息不存在！");
        }

    }
}