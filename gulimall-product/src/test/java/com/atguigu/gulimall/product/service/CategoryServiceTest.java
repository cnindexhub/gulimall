package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findCatelogPath() {
        List<Long> catelogPath = categoryService.findCatelogPath(225L);
        log.info("查找的分类路径：{}", catelogPath);
    }

    @Test
    public void updateDetail() {
//        {
//            "catId": 225,
//                "name": "手机",
//                "icon": "1",
//                "productUnit": "1"
//        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCatId(225L);
        categoryEntity.setName("手机");
        categoryEntity.setIcon("1");
        categoryEntity.setProductUnit("1");
        log.info("入参：{}", categoryEntity);
        categoryService.updateDetail(categoryEntity);
        log.info("修改成功！");
    }
}