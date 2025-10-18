package com.swagat.spring_mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String  home(){
        System.out.println("Home page requested");
        return "index.jsp";
    }
}
