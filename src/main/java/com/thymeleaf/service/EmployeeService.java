package com.thymeleaf.service;

import com.thymeleaf.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee>lists();


    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_Id);

    List<Employee> search(Integer employee_id, String employeeName, String department, String address);

    boolean isEmploeeValid(Integer employee_Id);
}
