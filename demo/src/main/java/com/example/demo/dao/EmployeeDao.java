package com.example.demo.dao;

import com.example.demo.dto.EmployeeDepartmentDto;
import com.example.demo.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    List<Employee>lists();

    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_id);

    List<Employee> search(
            @Param("employee_id") Integer employee_id,
            @Param("department") String department,
            @Param("employee_name") String employee_name,
            @Param("address") String address);

    List<EmployeeDepartmentDto> getEmployeesWithDepartments();
}
