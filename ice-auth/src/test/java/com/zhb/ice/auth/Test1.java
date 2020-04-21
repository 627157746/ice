package com.zhb.ice.auth;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/17 16:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {
    @Autowired
    StringEncryptor jasyptStringEncryptor;
    @Test
    public void test1(){
        System.out.println("abc");
        System.out.println(jasyptStringEncryptor.encrypt("123456"));

    }

}
