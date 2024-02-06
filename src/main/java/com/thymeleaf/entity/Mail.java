package com.thymeleaf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Mail {
    public Mail() {
    }

    private String id;
    private String from;
    @NotEmpty(message = "收件人不能为空")
    private String to;
    @NotEmpty(message = "邮件标题不能为空")
    private String subject;
    @NotEmpty(message = "邮件内容不能为空")
    private String text;
    private Date sentDate;
    private String status;
    private String error;

    public Mail(String id, String from, String to, String subject, String text, Date sentDate, String status, String error) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
        this.sentDate = sentDate;
        this.status = status;
        this.error = error;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }



}
