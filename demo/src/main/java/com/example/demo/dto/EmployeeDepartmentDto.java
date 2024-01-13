package com.example.demo.dto;

public class EmployeeDepartmentDto {
    public EmployeeDepartmentDto(Integer employee_id, String employee_name, String department, String address, String sex) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.department = department;
        this.address = address;
        this.sex = sex;
    }
    Integer employee_id;
    String employee_name;
    String department;
    String address;
    String sex;

    public Integer getEmployee_id() {
        return employee_id;
    }
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment_name(String department_name) {
        this.department = department;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
