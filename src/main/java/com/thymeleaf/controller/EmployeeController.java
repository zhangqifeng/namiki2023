package com.thymeleaf.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thymeleaf.dto.EmployeeDepartmentDto;
import com.thymeleaf.entity.Employee;
import com.thymeleaf.service.EmployeeService;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thymeleaf.entity.Employee;
import com.thymeleaf.service.EmployeeService;


@Controller
@RequestMapping("employee")
public class EmployeeController {

    private static final Logger log= LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;
    @Value("${resume.file.dir}")
    private String realPath;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //多条件联合搜索功能。（支持模糊查询）
    @RequestMapping("search")
    public String search(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,
                         @RequestParam(required = false) Integer employee_id,
                         @RequestParam(required = false) String employee_name,
                         @RequestParam(required = false) String department,
                         @RequestParam(required = false) String address, Model model){
        PageHelper.clearPage();
        PageHelper.startPage(pageNum,5);
        List<EmployeeDepartmentDto>employee=employeeService.search(employee_id,employee_name,department,address);
        PageInfo<EmployeeDepartmentDto> pageInfo = new PageInfo<>(employee);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("employee_id",employee_id);
        model.addAttribute("employee_name",employee_name);
        model.addAttribute("department",department);
        model.addAttribute("address",address);
        return "emplist";
    }
    //删除功能。
    @RequestMapping("delete")
    public String delete(Integer employee_id,RedirectAttributes ra){
        log.debug("删除的员工id:{}",employee_id);
        String oldResume = employeeService.findById(employee_id).getResume();
        if (oldResume != null) {
            File file = new File(realPath, oldResume);
            file.delete();
        }
        employeeService.delete(employee_id);
        ra.addFlashAttribute("msg1","削除成功しました!");
        return "redirect:/employee/lists";
    }
    //查询指定员工的详细信息并且跳转到更新画面。
    @RequestMapping("detail")
    public String detail(Integer employee_id,Model model) {
        log.debug("当前查询员工id:{}", employee_id);
        Employee employee = employeeService.findById(employee_id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments",employeeService.findDepartments());
        model.addAttribute("positionRanks",employeeService.findPositionRank());
        return "updateEmp";
    }
    @RequestMapping("update")
    public String update(@ModelAttribute("employee")@Valid Employee employee, BindingResult rs,RedirectAttributes ra,Model model,MultipartFile resumeFile) throws IOException {
        log.debug("更新之后员工信息:社員名:{},役職名:{},性別:{},部署名:{},住所:{},雇用形態:{},入社年月日:{},誕生日:{}",
                employee.getEmployee_name(), employee.getJob_title()
                ,employee.getSex(), employee.getDepartment(), employee.getAddress(), employee.getEmployment_status(), employee.getHire_date(), employee.getBirth_date());
        if (rs.hasErrors()){
            return "updateEmp";
        } else if (employee.getBirth_date() != null && !employeeService.isBirthDateValid(employee.getBirth_date())) {
            model.addAttribute("errorMsg", "社員年齢を18歳～60歳にしてください");
            return "updateEmp";
        } else {
            if (!resumeFile.isEmpty()) {
                String oldResume = employeeService.findById(employee.getEmployee_id()).getResume();
                if (oldResume != null) {
                    File file = new File(realPath, oldResume);
                    file.delete();
                }
                String originalFilename = resumeFile.getOriginalFilename();
                String newFileName = getNewFileName(resumeFile, originalFilename);
                employee.setResume(newFileName);
            }
        }

        employeeService.update(employee);
        ra.addFlashAttribute("msg2", "更新成功しました！");
        return "redirect:/employee/lists";
    }
@RequestMapping("download")
public void download( String resume, HttpServletResponse response) throws IOException {
        File file = new File(realPath,resume);
        FileInputStream is =new FileInputStream(file);
        response.setHeader("content-disposition","attachment;filename=");
        ServletOutputStream os = response.getOutputStream();
    int len=0;
    byte[] bytes=new byte[1024];
    while (true){
        len = is.read(bytes);
        if (len == -1) break;
        os.write(bytes,0,len);
    }
    is.close();
}
    //新增员工页面，初始化新增表单。
    @RequestMapping("add")
    public String addForm(Model model){
        model.addAttribute("employee",new Employee());
        model.addAttribute("departments",employeeService.findDepartments());
        model.addAttribute("positionRanks",employeeService.findPositionRank());
        return "addEmp" ;
    }
    //提交新增员工的表单，若新增成功则跳转到员工列表页面。
    @RequestMapping ("save")
    public String save(@ModelAttribute("employee")@Valid Employee employee,BindingResult bindingResult, Model model, RedirectAttributes ra, MultipartFile resumeFile) throws IOException {
        log.debug("社員番号:{},社員名:{},役職名:{},性別:{},部署名:{},住所:{},雇用形態:{},入社年月日:{},誕生日:{},密码:{}",
                employee.getEmployee_id(),employee.getEmployee_name(),employee.getJob_title()
        ,employee.getSex(),employee.getDepartment(),employee.getAddress(),employee.getEmployment_status(),employee.getHire_date(),employee.getBirth_date(),employee.getEmployee_password());
        String originalFilename = resumeFile.getOriginalFilename();
        log.debug("履历名称：{}",originalFilename);
        log.debug("履历大小：{}",resumeFile.getSize());
        log.debug("上传的路径:{}",realPath);
        //先判断用户输入是否为空，再判断新增的员工ID是否重复。
        if (bindingResult.hasErrors()) {
            return "addEmp";
        }else if(employeeService.isEmployeeValid(employee.getEmployee_id())){
            model.addAttribute("errorMsg","この社員番号がすでに登録されています");
            return "addEmp";
        }else if (employee.getBirth_date()!=null && !employeeService.isBirthDateValid(employee.getBirth_date())){
            model.addAttribute("errorMsg2","社員年齢を18歳～60歳にしてください");
            return "addEmp";
        }
        else if (!resumeFile.isEmpty()){
            String newFileName = getNewFileName(resumeFile, originalFilename);
            employee.setResume(newFileName);
        }
            if (employee.getEmployee_password()==null||employee.getEmployee_password().isEmpty())
                employee.setEmployee_password("123456");
            employeeService.save(employee);
        ra.addFlashAttribute("msg3","登録完了しました!");
            return "redirect:/employee/lists";

    }
    private String getNewFileName(MultipartFile resumeFile, String originalFilename) throws IOException {
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = fileNamePrefix+fileNameSuffix;
        resumeFile.transferTo(new File(realPath,newFileName));
        return newFileName;
    }

    //查询所有员工并分页。
    @RequestMapping("lists")
    public String getAllUser(Model model,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum
                            ){
        PageHelper.clearPage();
        //默认第一页开始、一行显示5个
        PageHelper.startPage(pageNum,5);
        //原有的查询方法（需写在startpage后）
      List<EmployeeDepartmentDto>employees=employeeService.getEmployeesWithDepartments();
        //返回查询到的信息到pageInfo里。
        PageInfo<EmployeeDepartmentDto> pageInfo = new PageInfo<>(employees);
        model.addAttribute("pageInfo",pageInfo);
        return "emplist"; //跳转到list.html
    }



}
