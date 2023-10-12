package com.thymeleaf.controller;
import com.thymeleaf.service.UserService;
import com.thymeleaf.entity.User;
import com.thymeleaf.utils.VerifyCodeUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("login")
    public String login(String user_name,String password,HttpSession session){
        try {
            log.debug("本地登录姓名:{}",user_name);
            log.debug("本地登录密码:{}",password);
            User user=userService.login(user_name,password);
            session.setAttribute("user",user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/login";
        }
        return "redirect:/employee/lists";
    }


    @RequestMapping ("register")
    public String register(User user, String code,HttpSession session){

        log.debug("用户名: {},密码: {},",user.getUser_name(),user.getPassword());
        log.debug("用户输入验证码: {}",code);
        try {
            //1.判断用户输入验证码和session中验证码是否一致
/* null pointer bug:code 没有加引号*/
            String sessionCode = session.getAttribute("code").toString();
            if(!sessionCode.equalsIgnoreCase(code))throw new RuntimeException("验证码输入错误!");
            //2.注册用户
            userService.register(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/register"; //注册失败回到注册
        }
        return  "redirect:/login";  //注册成功跳转到登录
    }

    @RequestMapping("generateImageCode")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        String code= VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("code",code);
        response.setContentType("image/png");
        ServletOutputStream os=response.getOutputStream();
        VerifyCodeUtils.outputImage(220,60,os,code);

    }


}
