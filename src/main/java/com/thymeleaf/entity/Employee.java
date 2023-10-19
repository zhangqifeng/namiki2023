package com.thymeleaf.entity;

import java.util.Date;

public class Employee {
    public Employee(Integer id, Integer employee_id, String employee_name, String sex, Date birth_date, String address, String department, String job_title, String employment_status, Date hire_date, Date create_date, Date update_date) {
        this.id = id;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.sex = sex;
        this.birth_date = birth_date;
        this.address = address;
        this.department = department;
        this.job_title = job_title;
        this.employment_status = employment_status;
        this.hire_date = hire_date;
        this.create_date = create_date;
        this.update_date = update_date;
    }

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer employee_id;
    private String employee_name;
    private String sex;

    private Date birth_date;

    private String address;
    private String department;
    private String job_title;
    private String employment_status;
    private Date hire_date;
    private Date create_date;

    private Date update_date;

    public Employee() {
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer emplyee_id) {
        this.employee_id = emplyee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }


}
