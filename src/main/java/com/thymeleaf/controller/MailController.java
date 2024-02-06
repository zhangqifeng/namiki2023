package com.thymeleaf.controller;
import com.thymeleaf.entity.Mail;
import com.thymeleaf.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("mail")
public class MailController {
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);
    private final EmailService emailService;
    @Autowired
    public MailController(EmailService emailService) {
        this.emailService = emailService;
    }


        @RequestMapping("/")
        public String mail(@RequestParam String to,@RequestParam String subject, Model model){
        Mail mail= new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText("您好，xxxx");
        mail.setFrom(emailService.getMailSendFrom());
        model.addAttribute("mail",mail);
        return "email";
        }

        @RequestMapping("send")
        public String sendMail(@ModelAttribute("mail") @Valid Mail mail, BindingResult bindingResult, RedirectAttributes rd, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
                return "email";
            }
            emailService.sendMail(mail);
            rd.addFlashAttribute("msg","邮件发送成功");
            return "redirect:/employee/lists";
        }
}
