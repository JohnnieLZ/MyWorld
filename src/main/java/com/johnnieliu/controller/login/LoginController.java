package com.johnnieliu.controller.login;


import com.johnnieliu.model.dto.Test;
import com.johnnieliu.model.entity.TbLpajxx;
import com.johnnieliu.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    public static Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private Test test;

    @Autowired()
    private TestService service;

    @ResponseBody
    @RequestMapping("/login")
    public TbLpajxx test() {
        test.setName("AAA");
        test.setSex("Man");
        test.setAge(188);
        System.out.println(test);
        log.info("----------log----------: "+test);

        TbLpajxx lpajxx = service.getFistDateById(112936L);
        log.info("----------Tb_Lpajxx--------:"+lpajxx);
        return lpajxx;
    }
}
