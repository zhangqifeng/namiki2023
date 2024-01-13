package com.example.demo.entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class Employee {

    public Employee() {

        employee_password = employee_password;
        mail_address = mail_address;
    }
//    String mailAddress;
//    String employeePassword;
    private Integer id;
    @NotNull(message = "社員番号を入力してください")
    private Integer employee_id;
    @NotEmpty(message = "社員の名前を入力してください")
    private String employee_name;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth_date;
    private String address;
    private String department;
    private String job_title;
    private String employment_status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hire_date;
    private Date create_date;
    private Date update_date;
    private Integer empInsuranceNumber;
    private Integer pensionNumber;
    private String posionRank;
    private String resume;
    private String employee_password;
    private String mail_address;

    public Employee(Integer id, Integer employee_id, String employee_name, String sex, LocalDate birth_date,
                    String address, String department, String job_title, String employment_status, LocalDate hire_date,
                    Date create_date, Date update_date, Integer empInsuranceNumber, Integer pensionNumber,
                    String posionRank, String resume, String employee_password, String mail_address) {
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
        this.empInsuranceNumber = empInsuranceNumber;
        this.pensionNumber = pensionNumber;
        this.posionRank = posionRank;
        this.resume = resume;
        this.employee_password = employee_password;
        this.mail_address = mail_address;
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

    public String getPosionRank() {
        return posionRank;
    }

    public void setPosionRank(String posionRank) {
        this.posionRank = posionRank;
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
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

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
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
    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }
}

