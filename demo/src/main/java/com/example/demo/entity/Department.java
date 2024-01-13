package com.example.demo.entity;


public class Department {
    public Department(Integer id, String department) {
        this.id = id;
        this.department = department;
    }

    public Department() {
    }


    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
