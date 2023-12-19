package com.thymeleaf.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thymeleaf.entity.Employee;
import com.thymeleaf.service.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// 多条件联合搜索功能。（支持模糊查询）
	@RequestMapping("search")
	public String search(Integer employee_id,
			String employee_name,
			String department,
			String address, Model model) {
		List<Employee> employee = employeeService.search(employee_id, employee_name, department, address);
		PageInfo<Employee> pageInfo = new PageInfo<>(employee);
		model.addAttribute("pageInfo", pageInfo);
		return "emplist";
	}

	// 删除功能。
	@RequestMapping("delete")
	public String delete(Integer employee_id, RedirectAttributes ra) {
		log.debug("删除的员工id:{}", employee_id);
		employeeService.delete(employee_id);
		ra.addFlashAttribute("msg1", "削除成功しました!");
		return "redirect:/employee/lists";
	}

	//查询指定员工的详细信息并且跳转到更新画面。
	@RequestMapping("detail")
	public String detail(Integer employee_id, Model model) {
		log.debug("当前查询员工id:{}", employee_id);
		Employee employee = employeeService.findById(employee_id);
		model.addAttribute("employee", employee);
		return "updateEmp";
	}

	@RequestMapping("update")
	public String update(@ModelAttribute("employee") @Valid Employee employee, BindingResult rs,
			RedirectAttributes ra) {
		log.debug("更新之后员工信息:社員名:{},役職名:{},性別:{},部署名:{},住所:{},雇用形態:{},入社年月日:{}",
				employee.getEmployee_name(), employee.getJob_title(), employee.getSex(), employee.getDepartment(),
				employee.getAddress(), employee.getEmployment_status(), employee.getHire_date());
		if (rs.hasErrors()) {
			return "updateEmp";
		} else
			employeeService.update(employee);
		ra.addFlashAttribute("msg2", "更新成功しました！");
		return "redirect:/employee/lists";
	}

	//新增员工页面，初始化新增表单。
	@RequestMapping("add")
	public String addForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "addEmp";
	}

	//提交新增员工的表单，若新增成功则跳转到员工列表页面。
	@RequestMapping("save")
	public String save(@ModelAttribute("employee") @Valid Employee employee, BindingResult bindingResult, Model model,
			RedirectAttributes ra) {
		log.debug("社員番号:{},社員名:{},役職名:{},性別:{},部署名:{},住所:{},雇用形態:{},入社年月日:{}",
				employee.getEmployee_id(), employee.getEmployee_name(), employee.getJob_title(), employee.getSex(),
				employee.getDepartment(), employee.getAddress(), employee.getEmployment_status(),
				employee.getHire_date());
		//先判断用户输入是否为空，再判断新增的员工ID是否重复。
		if (bindingResult.hasErrors()) {
			return "addEmp";
		} else if (employeeService.isEmployeeValid(employee.getEmployee_id())) {
			model.addAttribute("errorMsg", "この社員番号がすでに登録されています");
			return "addEmp";
		} else {
			employeeService.save(employee);
			ra.addFlashAttribute("msg3", "登録完了しました!");
			return "redirect:/employee/lists";
		}
	}

	//查询所有员工并分页。
	@RequestMapping("lists")
	public String getAllUser(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
		PageHelper.clearPage();

		//默认第一页开始、一行显示5个
		PageHelper.startPage(pageNum, 5);
		//原有的查询方法（需写在startpage后）
		List<Employee> employees = employeeService.lists();
		//返回查询到的信息到pageInfo里。
		PageInfo<Employee> pageInfo = new PageInfo<>(employees);
		model.addAttribute("pageInfo", pageInfo);
		return "emplist"; //跳转到list.html
	}

}
