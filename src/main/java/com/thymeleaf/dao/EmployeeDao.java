package com.thymeleaf.dao;

import com.thymeleaf.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDao {
    List<Employee>lists();

    void save(Employee employee);

    Employee findById(Integer emplyee_id);

    void update(Employee employee);

    void delete(Integer emplyee_Id);

    List<Employee> search(@Param("emplyee_id") Integer emplyee_id,
                          @Param("employee_name") String employee_Name,
                          @Param("department") String department,
                          @Param("address") String address);
}
