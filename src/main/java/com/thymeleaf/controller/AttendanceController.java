package com.thymeleaf.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thymeleaf.entity.Attendance;
import com.thymeleaf.entity.Employee;
import com.thymeleaf.service.EmployeeService;
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
import java.util.List;

@Controller
@RequestMapping("worker")
public class AttendanceController {
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    @Autowired
    public AttendanceController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @RequestMapping("loging")
    public String loginForm(Model model){
        model.addAttribute("employee",new Employee());
        return "employeelogin";
    }
    @RequestMapping("login")
    public String login(@ModelAttribute("employee") Employee employee, Model model, RedirectAttributes ra, HttpSession session) {
        log.debug("本地登录姓名:{}", employee.getEmployee_id());
        log.debug("本地登录密码:{}", employee.getEmployee_password());
        Integer employee_id= employee.getEmployee_id();
        String employee_password = employee.getEmployee_password();
       String employee_name = employeeService.findById(employee_id).getEmployee_name();
        //用户名与密码的校验。
        if (employee_id==null){
            model.addAttribute("error1","社員番号を入力してください");
            return "employeelogin";
        }
        else if(employee_password.isEmpty()){
            model.addAttribute("error2","パスワードを入力してください");
            return "employeelogin";
        }
        else if (!employeeService.isPasswordValid(employee_id,employee_password)) {
            model.addAttribute("errorMsg", "ユーザまたはパスワードが違います");
            return "employeelogin";
        }
        ra.addFlashAttribute("msg4","ログイン成功しました!");
        session.setAttribute("employee_id",employee_id);
        session.setAttribute("employee_name",employee_name);
        return "redirect:/worker/attendance?employee_id=" + employee_id;
    }
    @RequestMapping("attendance")
    public String getAttendance(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam Integer employee_id)
    {
        PageHelper.clearPage();
        PageHelper.startPage(pageNum,5);
        List<Attendance>attendances=employeeService.getAllAttendances(employee_id);
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        model.addAttribute("pageInfo",pageInfo);
        return "attendance";
    }
    @RequestMapping("search")
    public String searchDate(Integer year,
                         Integer month,
                         Integer day,
                         Model model){

        PageHelper.clearPage();
        PageHelper.startPage(1,5);
        List<Attendance>attendances=employeeService.searchDate(year,month,day);
        PageInfo<Attendance> pageInfo = new PageInfo<>(attendances);
        model.addAttribute("pageInfo",pageInfo);
        return "attendance";
    }
    @RequestMapping("clocking")
    public String clockForm(Model model){
        model.addAttribute("attendance",new Attendance());
        return "clock";
    }
    @RequestMapping("clock")
    public String clock(@ModelAttribute("attendance")@Valid Attendance attendance,HttpSession session,BindingResult bindingResult,RedirectAttributes ra,Model model){
        log.debug("入社时间:{},退社时间:{}",attendance.getStart_date(),attendance.getEnd_date());
if (bindingResult.hasErrors()){
    return "clock";
}
Integer employee_id= (Integer) session.getAttribute("employee_id");
attendance.setEmployee_id(employee_id);
employeeService.clock(attendance);
        return "redirect:/worker/attendance?employee_id=" + employee_id;
    }


}
