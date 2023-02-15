package com.atguigu.gulimall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1、整合Mybatis-Plus
 *      1)、导入依赖
 *      <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus-boot-starter</artifactId>
 *             <version>3.5.3.1</version>
 *             <type>pom</type>
 *     </dependency>
 *     2)、配置
 *          1、配置数据源：
 *              1)、导入数据库驱动。
 *              2)、在application.yml配置数据源相关信息
 *          2、配置MyBatis-Plus
 *              1）、配置@MapperScan
 *              2)、在application.yml中配置xml、id自增
 */
@MapperScan("com.atguigu.gulimall.product.dao")
@SpringBootApplication
public class GulimallProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallProductApplication.class, args);
    }

}
