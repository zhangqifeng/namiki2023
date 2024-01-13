package com.example.demo.dao;

import com.example.demo.entity.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> findAll();
}
