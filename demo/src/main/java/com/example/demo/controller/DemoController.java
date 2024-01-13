package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("demo")
public class DemoController {
    @RequestMapping("demo")
    public String demo(Model model){
        model.addAttribute("msg", "hello thymeleaf");
        return"demo";
    }
    @RequestMapping("login")
    public String login(){
        return "login";
    }
}
