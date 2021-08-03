package com.crhms.security.client.controller;

import com.alibaba.fastjson.JSON;
import com.example.common.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;

/**
 * @author XieShaoping
 */
@RestController
public class HelloController {

    @Autowired
    private OAuth2RestTemplate restTemplate;

    @Resource
    private ResourceService resourceService;

    @GetMapping("/hello")
    public String hello (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         authentication.getPrincipal();
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        "http://localhost:9002/auth/hello",
                        HttpMethod.GET,null,String.class);

        String result = restExchange.getBody();
        return result;
    }

    @GetMapping("/helloResource")
    public String helloResource (){
        return resourceService.hello();
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public Object test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            Object details = ((OAuth2Authentication) authentication).getUserAuthentication().getDetails();
            LinkedHashMap<String, ?> map = (LinkedHashMap<String, ?>) ((LinkedHashMap<String, ?>) (details)).get("principal");
            LoginUser loginUser = JSON.parseObject(JSON.toJSONString(map), LoginUser.class);
            loginUser.eraseCredentials();
        }
        return "hahah";
    }

    @GetMapping("/test1")
    public String test1 (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String result = resourceService.hello();
        return "result";
    }
}
