package com.thymeleaf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thymeleaf.entity.Employee;

public interface EmployeeDao {
    List<Employee>lists();

    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_id);

    List<Employee> search( @Param("employee_id") Integer employeeId,
                           @Param("employee_name") String employee_Name,
                           @Param("department") String department,
                           @Param("address") String address);


}
