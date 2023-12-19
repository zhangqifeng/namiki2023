package com.thymeleaf.entity;

import java.util.Date;

public class Attendance {
    public Attendance() {
    }

    public Attendance(Integer record_id, Integer employee_id, Date attendance_date, String status, Date start_date, Date end_date) {
        this.record_id = record_id;
        this.employee_id = employee_id;
        this.attendance_date = attendance_date;
        this.status = status;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    private Integer record_id;
    private Integer employee_id;
    private Date attendance_date;
    private String status;
    private Date start_date;
    private Date end_date;


    public Integer getRecord_id() {
        return record_id;
    }

    public void setRecord_id(Integer record_id) {
        this.record_id = record_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Date getAttendance_date() {
        return attendance_date;
    }

    public void setAttendance_date(Date attendance_date) {
        this.attendance_date = attendance_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }


}
