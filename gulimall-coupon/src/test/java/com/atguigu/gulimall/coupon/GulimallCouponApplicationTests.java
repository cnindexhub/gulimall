package com.atguigu.gulimall.coupon;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class GulimallCouponApplicationTests {

    @Test
    public void contextLoads() {

    }

    public static void main(String[] args) {
        String str = "123";
        int a = 1;
        System.out.println(Integer.valueOf(str) > a);
        System.out.println(System.currentTimeMillis());
        byte[] bytes = str.getBytes();
        new String(bytes);
    }

}

abstract class BaseClass {

    abstract void foo();
}
class SubClass extends BaseClass {

    @Override
    void foo() {

    }
}
