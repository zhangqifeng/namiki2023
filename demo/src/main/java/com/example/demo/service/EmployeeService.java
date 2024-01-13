package com.example.demo.service;

import com.example.demo.dto.EmployeeDepartmentDto;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.entity.PositionRank;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Employee> lists();

    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_Id);


    List<Employee> search(Integer employee_id, String department, String employee_name, String address);

    boolean isEmployeeValid(Integer employee_Id);

    boolean isBirthDateValid(LocalDate birth_date);

    List<Department> findDepartments();

    List <PositionRank>findPositionRank();

    List<EmployeeDepartmentDto> getEmployeesWithDepartments();

    boolean isPasswordValid(Integer employeeId, String employeePassword);

    List<Attendance> getAllAttendances(Integer employeeId);

    List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer employeeId);

    void clock(Attendance attendance);

    Attendance findByRecord(Integer recordId);

    void updateAttendance(Attendance attendance);
}
