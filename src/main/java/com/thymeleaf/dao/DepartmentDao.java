package com.thymeleaf.dao;

import com.thymeleaf.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll();
}
