package com.thymeleaf.dao;

import com.thymeleaf.dto.EmployeeDepartmentDto;
import com.thymeleaf.entity.Attendance;
import com.thymeleaf.entity.Department;
import com.thymeleaf.entity.Employee;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface EmployeeDao {
    List<Employee>lists();

    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_id);

    List<EmployeeDepartmentDto> search( @Param("employee_id") Integer employee_id,
                           @Param("employee_name") String employee_Name,
                           @Param("department") String department,
                           @Param("address") String address);
    List<EmployeeDepartmentDto>getEmployeesWithDepartments();
    List<Attendance>getAllAttendances(Integer employee_id);

    List<Attendance> searchDate(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day,
            @Param("employee_id")Integer employee_id);

    void clock(Attendance attendance);

    Attendance findByRecord(Integer recordId);

    void updateAttendance(Attendance attendance);
}
