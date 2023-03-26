package com.atguigu.gulimall.thirdparty;

import com.aliyun.oss.OSSClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GulimallThirdPartyApplicationTests {

    @Autowired
    private OSSClient ossClient;

    @Test
   public void contextLoads() {
        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("C:\\Users\\dengpengfei\\Pictures\\Saved Pictures\\b.jpg")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ossClient.putObject("poirotengine", "b2.jpg", bufferedInputStream);
        ossClient.shutdown();
        System.out.println("上传成功！");
    }

}
