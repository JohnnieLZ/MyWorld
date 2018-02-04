package com.johnnieliu.controller.login;


import com.johnnieliu.dao.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public void test() {
        new Test().test();
    }
}
