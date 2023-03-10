package com.atguigu.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 如何使用Nacos作为配置中心统一管理配置
 * 1)、引入依赖
 *         <dependency>
 *             <groupId>com.alibaba.cloud</groupId>
 *             <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *         </dependency>
 * 2)、创建bootstrap.properties
 *     spring.application.name=gulimall-coupon
 *     spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 * 3）、需要给配置中心默认添加一个叫 数据集（Data Id）gulimall-coupon.properties, 默认规则：应用名.properties
 * 4）、给 应用名.properties 添加任何配置
 * 5）、动态获取配置。
 * @RefreshCope 动态获取并刷新配置
 * @Value("${配置项的名称}")：获取到配置
 * 如果配置中心和当应用的配置文件中都配置了相同的项，优先使用配置中心的配置
 * 2、细节
 * 1)、命名空间：配置隔离；
 *      默认：public(保留空间)，默认新增的所有配置都在public空间。
 *      1、开发，测试，生产：利用命名空间来做环境隔离
 *          注意：在bootstrap.properties：配置上，需要使用哪个命名空间下的配置
 *          spring.cloud.nacos.config.namespace=249356ba-2aa7-4056-b017-3f74182bc4b1
 *      2、每一个微服务之间互相隔离配置，每一个微服务都创建自己的命名空间，只加载自己的命名空间下的配置
 * 2)、配置集：所有配置的集合
 * 3)、配置集ID：类似于配置文件名。
 *    Data ID：类似于配置文件名。
 * 4)、配置分组
 *    默认所有的配置都在DEFAULT_GROUP中
 *    1111、618、1212
 *
 * 项目中使用：每一个微服务创建自己的命名空间，使用配置分组来区分环境：dev、prod、test
 *
 * 3、同时加载多个配置
 * 1)、微服务任何配置信息，任何配置文件都能放在配置中心中
 * 2)、只需要在bootstrap.properties说明加载配置中心哪些配置文件即可
 * 3)、@Value、@ConfigurationProperties...
 * 以前SpringBoot使用任何方式从配置中心取值都是可以的
 * 配置中心有的优先使用配置中心的配置文件
 *
 */
@EnableDiscoveryClient
@SpringBootApplication
public class GulimallCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallCouponApplication.class, args);
    }

}
