package com.webstore.controller;

import com.constant.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = Constant.HOME_URL, method = RequestMethod.GET)
    public String home() {
        return Constant.ORDERS_REDIRECT_URL;
    }

}

