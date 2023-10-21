package com.thymeleaf.controller;
import com.thymeleaf.service.UserService;
import com.thymeleaf.entity.User;
import com.thymeleaf.utils.VerifyCodeUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

//用户退出并清除账号，返回登录界面。
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/loging";
    }
//登录界面，初始化登录表单。
    @RequestMapping("loging")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
//提交登录请求，若成功则跳转到员工列表页面。
    @RequestMapping("login")
    public String login(@ModelAttribute("user") @Valid User user,
                        BindingResult rs, Model model) {
        log.debug("本地登录姓名:{}", user.getUser_name());
        log.debug("本地登录密码:{}", user.getPassword());

        String user_name = user.getUser_name();
        String password = user.getPassword();
        //表单校验和用户名与密码的校验。
        if (rs.hasErrors() || !userService.isUserValid(user_name, password)) {
            model.addAttribute("errorMsg", "ユーザまたはパスワードが違います");
            return "login";
        } else {

            return "redirect:/employee/lists";
        }
    }




    @RequestMapping ("register")
    public String register(User user, String code,HttpSession session){
        log.debug("用户名: {},密码: {},",user.getUser_name(),user.getPassword());
        log.debug("用户输入验证码: {}",code);
        try {
            //1.判断用户输入验证码和session中验证码是否一致
/* null pointer bug:code 没有加引号*/
            String sessionCode = session.getAttribute( "code").toString();
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
