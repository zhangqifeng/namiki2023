package com.thymeleaf.service;

import com.thymeleaf.dto.EmployeeDepartmentDto;
import com.thymeleaf.entity.*;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {
    List<Department>findDepartments();
    List<PositionRank>findPositionRank();
    List<EmployeeDepartmentDto>getEmployeesWithDepartments();
    void save(Employee employee);

    Employee findById(Integer employee_id);

    void update(Employee employee);

    void delete(Integer employee_Id);

   List<EmployeeDepartmentDto> search(Integer employee_id, String employeeName, String department, String address);

    boolean isEmployeeValid(Integer employee_Id);
    boolean isBirthDateValid(LocalDate birth_date);
    boolean isPasswordValid(Integer employee_id,String employee_password);
    List<Attendance>getAllAttendances(Integer employee_id);


    List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer employee_id);


    void clock( Attendance attendance);

    Attendance findByRecord(Integer recordId);

    void updateAttendance(Attendance attendance);
}
