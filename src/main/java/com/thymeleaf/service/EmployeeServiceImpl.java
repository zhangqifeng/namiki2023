package com.thymeleaf.service;

import com.thymeleaf.dao.EmployeeDao;
import com.thymeleaf.dao.UserDao;
import com.thymeleaf.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;
@Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
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
    public Employee findById(Integer emplyee_Id) {
       return employeeDao.findById(emplyee_Id);
    }

    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public void delete(Integer emplyee_Id) {
        employeeDao.delete(emplyee_Id);
    }

    @Override
    public List<Employee> search(Integer emplyee_id, String employeeName, String department, String address) {
    return employeeDao.search(emplyee_id,employeeName,department,address);
    }
}
