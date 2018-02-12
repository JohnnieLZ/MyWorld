package com.publicwelfareplatform.controller.login;


import com.publicwelfareplatform.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    public static Logger log = Logger.getLogger(LoginController.class);

    @Autowired()
    private LoginService service;

    @ResponseBody
    @RequestMapping("/login")
    public void userLogin() {
    }
}
