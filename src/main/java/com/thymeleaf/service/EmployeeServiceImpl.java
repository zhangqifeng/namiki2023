package com.thymeleaf.service;

import com.thymeleaf.dao.DepartmentDao;
import com.thymeleaf.dao.EmployeeDao;
import com.thymeleaf.dao.PositionRankDao;
import com.thymeleaf.dto.EmployeeDepartmentDto;
import com.thymeleaf.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
    private DepartmentDao departmentDao;
    private PositionRankDao positionRankDao;
@Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao ,DepartmentDao departmentDao, PositionRankDao positionRankDao) {
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
    public void save(Employee employee) {

    employeeDao.save(employee);
    }

    @Override
    public Employee findById(Integer employee_Id) {
       return employeeDao.findById(employee_Id);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer employee_Id) {
        employeeDao.delete(employee_Id);
    }

    @Override
    public List<EmployeeDepartmentDto>search(Integer employee_id, String employeeName, String department, String address) {
    return employeeDao.search(employee_id,employeeName,department,address);
    }

    @Override
    public boolean isEmployeeValid(Integer employee_Id) {

        return employeeDao.findById(employee_Id) != null;

    }

    @Override
    public boolean isBirthDateValid(LocalDate birth_date) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birth_date, currentDate);
        int age = period.getYears();
           return age >= 18 && age <= 60;
    }

    @Override
    public boolean isPasswordValid( Integer employee_id,String employee_password) {
        Employee employee=employeeDao.findById(employee_id);
        return employee!=null&&employee.getEmployee_password().equals(employee_password);

    }

    @Override
    public List<Attendance> getAllAttendances(Integer employee_id) {
        return employeeDao.getAllAttendances(employee_id);
    }

    @Override
    public List<Attendance> searchDate(Integer year, Integer month, Integer day, Integer employee_id) {
        return employeeDao.searchDate(year,month,day,employee_id);
    }

    @Override
    public void clock(Attendance attendance) {
       employeeDao.clock(attendance);
    }

    @Override
    public Attendance findByRecord(Integer recordId) {
        return employeeDao.findByRecord(recordId);
    }

    @Override
    public void updateAttendance(Attendance attendance) {
        employeeDao.updateAttendance(attendance);
    }

    @Override
    public void generateDailyAttendance(Integer employeeId, int year, int month) {
        if (employeeDao.hasAttendanceRecords(employeeId, year, month)) {
            // 如果已存在记录，则不重新创建
            return;
        }
        int daysInMonth = YearMonth.of(year, month).lengthOfMonth();  // 获取当月天数
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate attendanceDate = LocalDate.of(year, month, day);
            // 为每一天创建一个新的 Attendance 对象
            Attendance attendance = new Attendance();
            attendance.setEmployee_id(employeeId);
            attendance.setAttendance_date(Date.valueOf(attendanceDate));  // 将 LocalDate 转换为 Date
            attendance.setStatus("欠勤");
            // 将新创建的考勤记录保存到数据库
           employeeDao.clock(attendance);
        }

    }

    @Override
    public List<Attendance> getMoreAttendances(Integer employeeId, Integer pageNum, Integer pageSize) {
        int startRow = (pageNum - 1) * pageSize + 1;
        int endRow = pageNum * pageSize;

        return employeeDao.getAttendancesInRange(employeeId, startRow, endRow);
    }
}
