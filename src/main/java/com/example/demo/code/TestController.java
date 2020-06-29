package com.example.demo.code;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author XieShaoping
 * @date 2020/6/19
 * @description
 */
@RestController
class TestController {

    @Resource
    TestService testService;

    @GetMapping("test")
    String test() {
        return testService.test();
    }

}
