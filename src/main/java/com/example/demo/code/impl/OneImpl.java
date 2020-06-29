package com.example.demo.code.impl;

import com.example.demo.code.TestService;
import org.springframework.stereotype.Service;

/**
 * @author XieShaoping
 * @date 2020/6/19
 * @description
 */
@Service
class OneImpl implements TestService {
    @Override
    public String test() {
        return "one";
    }
}
