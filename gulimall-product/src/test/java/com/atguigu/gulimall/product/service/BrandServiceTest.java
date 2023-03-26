package com.atguigu.gulimall.product.service;

import com.atguigu.gulimall.product.entity.BrandEntity;
import jdk.nashorn.internal.ir.BreakableNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class BrandServiceTest {

    @Autowired
    private BrandService brandService;

    /**
     * 测试 修改品牌信息 及其相关信息处理
     */
    @Test
    public void updateDetail() {
//        {
//            "t": 1679837236495,
//                "brandId": 1,
//                "name": "华为",
//                "logo": "http://poirotengine.oss-cn-shenzhen.aliyuncs.com/2023-03-24/72e5c386-d582-4249-875f-fcc6b8020bb0_R-C.jpg",
//                "descript": "华为",
//                "showStatus": 1,
//                "firstLetter": "H",
//                "sort": 0
//        }
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setBrandId(1L);
        brandEntity.setName("华为1");
        brandEntity.setSort(1);
        brandEntity.setLogo("http://poirotengine.oss-cn-shenzhen.aliyuncs.com/2023-03-24/72e5c386-d582-4249-875f-fcc6b8020bb0_R-C.jpg");
        brandEntity.setDescript("华为1");
        brandEntity.setFirstLetter("H");
        brandEntity.setShowStatus(1);
        log.info("入参：{}", brandEntity);
        brandService.updateDetail(brandEntity);
        log.info("修改成功！");
    }
}