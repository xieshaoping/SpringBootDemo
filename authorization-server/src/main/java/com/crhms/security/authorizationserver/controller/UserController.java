package com.crhms.security.authorizationserver.controller;

import com.example.common.LoginUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        //LoginUser user = ((LoginUser)((OAuth2Authentication) principal).getPrincipal());
        System.out.println(principal);
        return principal;
    }

    @RequestMapping("/hello")
    public String hello() {
        System.out.println("This is a message from authentication server");
        return "This is a message from authentication server";
    }

    @RequestMapping("auth/hello")
    public String hello1() {
        System.out.println("This is a message from authentication server");
        return "This is a message from authentication server";
    }
}
