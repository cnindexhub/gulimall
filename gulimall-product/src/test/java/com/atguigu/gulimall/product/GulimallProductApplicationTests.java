package com.atguigu.gulimall.product;

import com.atguigu.gulimall.product.entity.BrandEntity;
import com.atguigu.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mysql.cj.result.Field;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallProductApplicationTests {

    @Autowired
    private BrandService brandService;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setDescript("华为");
//        brandEntity.setName("华为");
//        brandEntity.setDescript("这是一个手机.....");
//        boolean success = brandService.save(brandEntity);
//        if (success) {
//            System.out.println("保存成功！");
//        } else {
//            System.out.println("保存失败！");
//        }

//        brandService.updateById(brandEntity);
        QueryWrapper<BrandEntity> brandEntityQueryWrapper = new QueryWrapper<>();
        brandEntityQueryWrapper.eq("brand_id", 1L);
        List<BrandEntity> list = brandService.list(brandEntityQueryWrapper);
        list.forEach((item) -> {
            System.out.println(item);
        });
    }

}

