package com.example.demo.service;
import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.PositionRankDao;
import com.example.demo.dto.EmployeeDepartmentDto;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entity.*;
import com.example.demo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;
    private PositionRankDao positionRankDao;
    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao,DepartmentDao departmentDao, PositionRankDao positionRankDao) {

        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
        this.positionRankDao = positionRankDao;
    }
    @Override
    public List<Department> findDepartments() {

        return departmentDao.findAll();
    }

    @Override
    public List<PositionRank> findPositionRank() {

        return positionRankDao.findAll();
    }

    @Override
    public List<EmployeeDepartmentDto> getEmployeesWithDepartments() {
        return employeeDao.getEmployeesWithDepartments();
    }

    @Override
    public boolean isPasswordValid(Integer employeeId, String employeePassword) {
        return false;
    }

    @Override
    public List<Attendance> getAllAttendances(Integer employeeId) {
        return null;
    }

    @Override
    public List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer employeeId) {
        return null;
    }

    @Override
    public void clock(Attendance attendance) {

    }

    @Override
    public Attendance findByRecord(Integer recordId) {
        return null;
    }

    @Override
    public void updateAttendance(Attendance attendance) {

    }

    @Override
    public List<Employee> lists() {
        return employeeDao.lists();
    }

    @Override
    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee findById(Integer employee_id) {
        return employeeDao.findById(employee_id);

    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer employee_id) {
        employeeDao.delete(employee_id);
    }

    @Override
    public List<Employee> search(Integer employee_id, String department, String employee_name, String address) {
        return employeeDao.search(employee_id, department, employee_name, address);
    }

    @Override
    public boolean isEmployeeValid(Integer employee_id) {
        return employeeDao.findById(employee_id) != null;
    }

    @Override
    public boolean isBirthDateValid(LocalDate birth_date){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birth_date, currentDate);
        int age = period.getYears();
                return age >= 18 && age <= 60;
    }






}
