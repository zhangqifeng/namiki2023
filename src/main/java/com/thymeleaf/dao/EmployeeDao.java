package com.thymeleaf.dao;

import com.thymeleaf.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    List<Employee>lists();

    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_id);

    List<Employee> search( @Param("employee_id") Integer employee_id,
                           @Param("employee_name") String employee_Name,
                           @Param("department") String department,
                           @Param("address") String address);


}
