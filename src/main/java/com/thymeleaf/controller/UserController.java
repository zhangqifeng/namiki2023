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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;

@Controller
@RequestMapping("user")

public class UserController {

    private static final Logger log= LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("register")

    public String register(User user, String code, HttpSession session){
    log.debug("用户名：{},密码：{}，",user.getUser_name(),user.getPassword());
    log.debug("用户输入验证码：{}",code);
        try {
            String sessionCode = session.getAttribute("code").toString();
            if (!sessionCode.equalsIgnoreCase(code))throw new RuntimeException("验证码错误！");
            userService.register(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "redirect:/register";

        }


        return "redirect:/login";

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
