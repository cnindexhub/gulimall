package com.atguigu.gulimall.member.feign;


import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 这是一个声明式的远程调用
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 测试获取所有优惠卷
     * @return
     */
    @RequestMapping("/coupon/coupon/member/list")
    public R membercoupons();
}
