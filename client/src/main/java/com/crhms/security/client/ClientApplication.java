package com.crhms.security.client;

import com.crhms.security.client.security.SsoLogoutFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.context.request.RequestContextListener;

/**
 * @author XieShaoping
 */
@SpringBootApplication
@EnableFeignClients
public class ClientApplication {


    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
        //System.out.println(new BCryptPasswordEncoder().encode("secret"));
        //System.out.println(new BCryptPasswordEncoder().matches("secret","$2a$10$to6S.0rCvoPonfOG9Zu0veDA0CiNi2BAM3CmjoAA7VwswJc9Gy4j2"));
    }

}
