package com.zhb.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/17 16:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Test1.class)
public class Test1 {


    @Test
    public void test1(){
        System.out.println(1);
    }

}
