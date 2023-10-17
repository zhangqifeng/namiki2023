package com.thymeleaf.service;

import com.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee>lists();


    void save(Employee employee);

    Employee findById(Integer emplyeeId);

    void update(Employee employee);
}
