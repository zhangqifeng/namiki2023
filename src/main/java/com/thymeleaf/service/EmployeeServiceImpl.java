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
}
