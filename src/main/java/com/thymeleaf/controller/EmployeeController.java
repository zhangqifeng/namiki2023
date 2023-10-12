package com.thymeleaf.controller;

import com.thymeleaf.entity.Employee;
import com.thymeleaf.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeService employeeService;
@Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

     @RequestMapping("lists")
    public String lists(Model model){
         log.debug("查询所有员工信息");
         List<Employee>employeeList= employeeService.lists();
         model.addAttribute("employeeList",employeeList);
         return "emplist";
     }


}
