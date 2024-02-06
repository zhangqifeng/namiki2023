package com.thymeleaf.service;

import com.thymeleaf.entity.Mail;

public interface EmailService {
     void sendMail(Mail mail);
     String getMailSendFrom();



}
