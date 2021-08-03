package com.crhms.security.client2.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author XieShaoping
 */
@RestController
public class Client2Controller {

    @RequestMapping("auth/hello")
    public Object hello(){
        SecurityContextHolder.getContext().getAuthentication();
        return "这里是client2";
    }

}
