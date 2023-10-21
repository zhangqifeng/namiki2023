package com.thymeleaf.controller;

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
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //多条件联合搜索功能。（支持模糊查询）
    @RequestMapping("search")
    public String search(Integer employee_id,
                         String employee_name,
                         String department,
                         String address, Model model){
        List<Employee>employeeList=employeeService.search(employee_id,employee_name,department,address);
        model.addAttribute("employeeList",employeeList);
        return "emplist";
    }

    //删除功能。
    @RequestMapping("delete")
    public String delete(Integer employee_id){
        log.debug("删除的员工id:{}",employee_id);
        employeeService.delete(employee_id);
        return "redirect:/employee/lists";
    }

    //更新员工列表功能。
    @RequestMapping("update")
    public String update(Employee employee)  {
        log.debug("更新之后员工信息:社員番号:{},社員名:{},役職名:{},性別:{},部署名:{},住所:{},雇用形態:{},入社年月日:{}",
                employee.getEmployee_id(),employee.getEmployee_name(),employee.getJob_title()
                ,employee.getSex(),employee.getDepartment(),employee.getAddress(),employee.getEmployment_status(),employee.getHire_date());
            employeeService.update(employee);

        return "redirect:/employee/lists";
    }

    //查询指定员工的详细信息并且跳转到更新画面。
    @RequestMapping("detail")
    public String detail(Integer employee_id,Model model) {
        log.debug("当前查询员工id:{}", employee_id);
        Employee employee = employeeService.findById(employee_id);
        model.addAttribute("employee", employee);
        return "updateEmp";
    }

    //新增员工页面，初始化新增表单。
    @RequestMapping("add")
    public String addForm(Model model){
        model.addAttribute("employee",new Employee());
        return "addEmp" ;
    }


    //提交新增员工的表单，若新增成功则跳转到员工列表页面。
    @RequestMapping ("save")
    public String save(@ModelAttribute("employee")@Valid Employee employee, BindingResult bindingResult,Model model){
        log.debug("社員番号:{},社員名:{},役職名:{},性別:{},部署名:{},住所:{},雇用形態:{},入社年月日:{}",
                employee.getEmployee_id(),employee.getEmployee_name(),employee.getJob_title()
        ,employee.getSex(),employee.getDepartment(),employee.getAddress(),employee.getEmployment_status(),employee.getHire_date());
        //先判断用户输入是否为空，再判断新增的员工ID是否重复。
        if (bindingResult.hasErrors()) {
            return "addEmp";
        }else if(employeeService.isEmploeeValid(employee.getEmployee_id())){
            model.addAttribute("errorMsg","この社員番号がすでに登録されています");
            return "addEmp";
        }
        else{
            employeeService.save(employee);
            return "redirect:/employee/lists";
        }
    }

    //查询所有员工。
     @RequestMapping("lists")
    public String lists(Model model){
         log.debug("查询所有员工信息");
         List<Employee>employeeList= employeeService.lists();
         model.addAttribute("employeeList",employeeList);
         return "emplist";
     }


}
