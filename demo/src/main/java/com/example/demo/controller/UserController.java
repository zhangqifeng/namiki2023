package com.example.demo.controller;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("loging")
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @RequestMapping("login")
    public String login(@ModelAttribute("user") @Valid User user,
                        BindingResult rs, Model model, RedirectAttributes ra){
        log.debug("本次登录姓名：{}", user.getUser_name());
        log.debug("本次登录密码：{}", user.getPassword());

        String user_name = user.getUser_name();
        String password = user.getPassword();
        //表单校验和用户名密码的校验
        if(rs.hasErrors()){
            return "login";
        }else if(!userService.isUserValid(user_name, password)){
            model.addAttribute("errorMsg", "ユーザーまたはパスワードが違います");
            return"login";
        }else {
            ra.addFlashAttribute("msg4", "ログイン成功しました！");
            return "redirect:/employee/lists";
        }
    }
    @RequestMapping("registering")
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        return "regist";
    }


    @RequestMapping("register")
    public String register(@ModelAttribute("user")@Valid User user, BindingResult rs, Model model, String code, HttpSession session, RedirectAttributes ra){

        log.debug("用户名：{}，密码：{}", user.getUser_name(), user.getPassword());
        log.debug("用户输入验证码：{}", code);

        String user_name= user.getUser_name();
        String sessionCode = session.getAttribute("code").toString();
        String password = user.getPassword();
            if (rs.hasErrors()){
                return"regist";
            } else if (!userService.isPasswordValid(password)) {
                model.addAttribute("errorMsg3", "半角英数字6文字~15文字にしてください");
                return "regist";
            } else if (!sessionCode.equalsIgnoreCase(code)) {
                model.addAttribute("errorMsg1", "確認コードが違います");
                return "regist";
            } else if (userService.isUserExisted(user_name)) {
                model.addAttribute("errorMsg2", "このユーザーは既に登録された");
                return "regist";
            }else {
                userService.register(user);
                ra.addFlashAttribute("msg", "成功に登録しました！");
                return "redirect:/user/loging";
            }
    }
    @RequestMapping("generateImageCode")
    public void generateImageCode(HttpSession session, HttpServletResponse response) throws IOException {
        String code = VerifyCodeUtils.generateVerifyCode(4);
        session.setAttribute("code",code);
        response.setContentType("image/png");
        ServletOutputStream os = response.getOutputStream();
        VerifyCodeUtils.outputImage(100, 40, os, code);
    }
}
