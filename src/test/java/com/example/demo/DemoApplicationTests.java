package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Iterator;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        File file = new File("E:\\360Downloads\\Software\\漏洞补丁目录");
        File[] files = file.listFiles();
        for (File f:files){
            System.out.println(f.getName()+"-"+f.isFile());
        }
        System.out.println("123");
    }



}
