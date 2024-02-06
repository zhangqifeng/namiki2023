package com.thymeleaf.service;

import com.thymeleaf.entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceIml implements EmailService{
    private Logger logger = LoggerFactory.getLogger(getClass());

    private JavaMailSenderImpl mailSender;
    @Autowired
    public EmailServiceIml(JavaMailSenderImpl mailSender){
        this.mailSender = mailSender;
    }

@Override
    public void sendMail(Mail mail) {
        try {
            checkMail(mail);
            sendMimeMail(mail);

        } catch (Exception e) {
            logger.error("发送邮件失败:", e);
            mail.setStatus("fail");
            mail.setError(e.getMessage());
        }

}


    private void checkMail(Mail mail) {
        if ( mail.getTo().isEmpty()) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (mail.getSubject().isEmpty()) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (mail.getText().isEmpty()) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }
    private void sendMimeMail(Mail mail) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            mail.setFrom(getMailSendFrom());
            messageHelper.setFrom(mail.getFrom());
            messageHelper.setTo(mail.getTo().split(","));
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());
            if (mail.getSentDate() == null || mail.getSentDate().toString().isEmpty()) {
                mail.setSentDate(new Date());
                messageHelper.setSentDate(mail.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());
            mail.setStatus("ok");
            logger.info("发送邮件成功：{}->{}", mail.getFrom(), mail.getTo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


@Override
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}
