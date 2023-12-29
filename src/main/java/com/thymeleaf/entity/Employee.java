package com.thymeleaf.entity;


import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class Employee {
    public Employee(Integer employee_id, String employee_name, String employee_password, String sex, LocalDate birth_date, String address, String mail_address, Integer phone_number, Integer department, Integer positionRank, String job_title, String employment_status, LocalDate hire_date, Date create_date, Date update_date, Integer empInsuranceNumber, Integer pensionNumber, String resume) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_password = employee_password;
        this.sex = sex;
        this.birth_date = birth_date;
        this.address = address;
        this.mail_address = mail_address;
        this.phone_number = phone_number;
        this.department = department;
        this.positionRank = positionRank;
        this.job_title = job_title;
        this.employment_status = employment_status;
        this.hire_date = hire_date;
        this.create_date = create_date;
        this.update_date = update_date;
        this.empInsuranceNumber = empInsuranceNumber;
        this.pensionNumber = pensionNumber;
        this.resume = resume;
    }

    @NotNull(message = "社員番号を入力してください！")
    private Integer employee_id;
    //"社員番号を入力してください！"
    @NotEmpty(message = "社員の名前を入力してください！")
    private String employee_name;

    private String employee_password;

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;
    public LocalDate getBirth_date() {
        return birth_date;
    }
    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    private String address;
    private String mail_address;

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    private Integer phone_number;

private Integer department;

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

private Integer positionRank;

    public Integer getPositionRank() {
        return positionRank;
    }

    public void setPositionRank(Integer positionRank) {
        this.positionRank = positionRank;
    }

    private String job_title;
    private String employment_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hire_date;
    private Date create_date;
    private Date update_date;
    private Integer empInsuranceNumber;
    private Integer pensionNumber;


    private String resume;

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Integer getEmpInsuranceNumber() {
        return empInsuranceNumber;
    }


    public void setEmpInsuranceNumber(Integer empInsuranceNumber) {
        this.empInsuranceNumber = empInsuranceNumber;
    }

    public Integer getPensionNumber() {
        return pensionNumber;
    }

    public void setPensionNumber(Integer pensionNumber) {
        this.pensionNumber = pensionNumber;
    }




    public Employee() {
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
    public LocalDate getHire_date() {
        return hire_date;
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
    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
    public String getEmployee_name() {
        return employee_name;
    }
    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
