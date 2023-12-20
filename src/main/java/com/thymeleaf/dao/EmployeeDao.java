package com.thymeleaf.dao;

<<<<<<< HEAD
import com.thymeleaf.dto.EmployeeDepartmentDto;
import com.thymeleaf.entity.Attendance;
import com.thymeleaf.entity.Department;
import com.thymeleaf.entity.Employee;
import org.apache.ibatis.annotations.Param;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
=======
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thymeleaf.entity.Employee;
>>>>>>> a3c84c173a29ca58163a12bbd59cf10875ea08cf

public interface EmployeeDao {
    List<Employee>lists();

    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_id);

<<<<<<< HEAD
    List<EmployeeDepartmentDto> search( @Param("employee_id") Integer employee_id,
=======
    List<Employee> search( @Param("employee_id") Integer employeeId,
>>>>>>> a3c84c173a29ca58163a12bbd59cf10875ea08cf
                           @Param("employee_name") String employee_Name,
                           @Param("department") String department,
                           @Param("address") String address);
    List<EmployeeDepartmentDto>getEmployeesWithDepartments();
    List<Attendance>getAllAttendances(Integer employee_id);

    List<Attendance> searchDate(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("day") Integer day);

    void clock(Attendance attendance);
}
