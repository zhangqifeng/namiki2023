package com.thymeleaf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("login").setViewName("login");
       registry.addViewController("regist").setViewName("regist");
       registry.addViewController("addEmp").setViewName("addEmp");
       registry.addViewController("emplist").setViewName("emplist");
       registry.addViewController("updateEmp").setViewName("updateEmp");
<<<<<<< HEAD
       registry.addViewController("employeelogin").setViewName("employeelogin");
       registry.addViewController("attendance").setViewName("attendance");
=======


>>>>>>> a3c84c173a29ca58163a12bbd59cf10875ea08cf
    }

}
