package com.thymeleaf.service;

import com.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee>lists();


    void save(Employee employee);

    Employee findById(Integer emplyee_Id);

    void update(Employee employee);

    void delete(Integer emplyee_Id);

    List<Employee> search(Integer emplyee_id, String employeeName, String department, String address);
}
