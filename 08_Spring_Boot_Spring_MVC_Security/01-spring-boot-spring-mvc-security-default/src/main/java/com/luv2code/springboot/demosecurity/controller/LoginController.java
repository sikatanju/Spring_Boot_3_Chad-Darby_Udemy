package com.luv2code.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage") // This mapping name should be same as from security config file
    public String showMyLoginPage() {
//        return "plain-login";
        return "fancy-login";
    }
}
