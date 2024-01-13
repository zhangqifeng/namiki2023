package com.example.demo.controller;

import com.example.demo.dto.EmployeeDepartmentDto;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("employee")
@Slf4j
public class EmployeeController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @RequestMapping("search")
    public  String search(
            @Param("employee_id") Integer employee_id,
            @Param("department") String department,
            @Param("employee_name") String employee_name,
            @Param("address") String address, Model model){
        List<Employee>employee = employeeService.search(employee_id, department, employee_name, address);
        PageInfo<Employee> pageInfo = new PageInfo<>(employee);
        model.addAttribute("pageInfo",pageInfo);
        return "emplist";
    }

    @RequestMapping("delete")
    public String delete(Integer employee_id, RedirectAttributes ra){
        log.debug("删除的员工id:{}", employee_id);
//        Employee employee = employeeService.findById(employee_id);
        employeeService.delete(employee_id);
        ra.addFlashAttribute("msg1", "削除成功しました");
        return "redirect:/employee/lists";
    }

    @RequestMapping("detail")
    public String detail(Integer employee_id, Model model){
        log.debug("当前查询员工id：{}", employee_id);
        Employee employee = employeeService.findById(employee_id);
        model.addAttribute("employee", employee);
        return "updateEmp";
    }

    @RequestMapping("update")
    public  String update(@ModelAttribute("employee")@Valid Employee employee, BindingResult rs, RedirectAttributes ra,
                          Model model, @RequestParam MultipartFile resumeFile)
            throws IOException {
        log.debug("社員番号:{}, 社員名:{}, 役職名:{}, 性別:{}, 部署名:{}, 住所:{}, 雇用形態:{}, 入社年月日:{}, 入社年月日:{}, " +
                        "文件名:{}, 文件大小:{}, 文件类型:{}",
                employee.getEmployee_id(), employee.getEmployee_name(), employee.getJob_title(),employee.getSex(),
                employee.getDepartment(), employee.getAddress(), employee.getEmployment_status(), employee.getHire_date()
                , resumeFile.getOriginalFilename(), resumeFile.getSize(), resumeFile.getContentType()
        );
        employeeService.update(employee);
        String originalFilename =resumeFile.getOriginalFilename();
        if (rs.hasErrors()){
            return "updateEmp";
        } else if (employee.getBirth_date() != null && !employeeService.isBirthDateValid(employee.getBirth_date())) {
            model.addAttribute("errorMsg", "社員年齢を18歳～60歳にしてください");
            return "updateEmp";
        }
         if (!resumeFile.isEmpty()) {
             String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
             String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
             String newFileName = fileNamePrefix + fileNameSuffix;
             resumeFile.transferTo(new File(realPath, newFileName));
             employee.setResume(newFileName);
         }
            employeeService.update(employee);
        ra.addFlashAttribute("msg2", "更新成功しました！");
        return "redirect:/employee/lists";
    }

    @RequestMapping("add")
    public String addForm(Model model, RedirectAttributes ra){
        model.addAttribute("employee",new Employee());
        model.addAttribute("departments",employeeService.findDepartments());
        model.addAttribute("positionRanks",employeeService.findPositionRank());
        ra.addFlashAttribute("msg3", "新規登録成功しました！");
        return "addEmp";
    }

    @Value("${file.upload.dir}")
    private String realPath;
    @RequestMapping("save")
    public  String save(@ModelAttribute("employee")@Valid Employee employee, BindingResult bindingResult,
                        RedirectAttributes ra, Model model, MultipartFile resumeFile) throws IOException {
        log.debug("社員番号:{}, 社員名:{}, 役職名:{}, 性別:{}, 部署名:{}, 住所:{}, 雇用形態:{}, 入社年月日:{}, 文件名:{}, 文件大小:{}, 文件类型:{}",
                employee.getEmployee_id(),
                employee.getEmployee_name(), employee.getJob_title(),employee.getSex(), employee.getDepartment(), employee.getAddress(),
                employee.getEmployment_status(), employee.getHire_date(), resumeFile.getOriginalFilename(), resumeFile.getSize(), resumeFile.getContentType());
        String originalFilename = resumeFile.getOriginalFilename();
        log.debug("履历名称：{}", originalFilename);
        log.debug("履历大小：{}", resumeFile.getSize());
        log.debug("上传的路径：{}", realPath);
        if (bindingResult.hasErrors()){
            return "addEmp";
        }else if(employeeService.isEmployeeValid(employee.getEmployee_id())){
            model.addAttribute("errorMsg", "この社員番号が既に登録されています");
            return "addEmp";
        }else if (employee.getBirth_date() != null && !employeeService.isBirthDateValid(employee.getBirth_date())) {
            model.addAttribute("errorMsg2", "社員年齢を18歳～60歳にしてください");
            return "addEmp";
        }
//        MultipartFile file 变量名要与form中的input type="file"标签name属性名一致
        else if (!resumeFile.isEmpty()) {
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = fileNamePrefix + fileNameSuffix;
            resumeFile.transferTo(new File(realPath,newFileName));
            employee.setResume(newFileName);

        }
            employeeService.save(employee);
            ra.addFlashAttribute("msg3", "登録完了しました！");
        return "redirect:/employee/lists";

    }

    //    文件下载
    @RequestMapping("download")
    public void download (String fileName, HttpServletResponse response) throws IOException {
        log.debug("当前下载文件名为：{}", fileName);
        log.debug("当前下载文件目录:{}",realPath);
        //1.去指定目录中读取文件
        File file = new File(realPath, fileName);
        //2.将文件读取为输入流
        FileInputStream is = new FileInputStream(file);
        //2.5再获取响应流之前，一定要设置以附件(attachment)形式下载
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
        //3.获取响应输出流
        ServletOutputStream os = response.getOutputStream();
        //4.输入流复制给输出流
//        int len =0;
//        byte[] b = new byte[1024];
//        while(true){
//            len = is.read(b);
//            if(len==-1)break;
//            os.write(b, 0, len);
//        }
        FileCopyUtils.copy(is, os);
        //5.释放资源
//        is.close();
    }


    @RequestMapping("lists")
    public String lists(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        PageHelper.clearPage();
        //默认从第一页开始，一行显示五个
        PageHelper.startPage(pageNum, 5);

        //原有的查询方法（需要写在startpage后）
//        List<Employee>employees = employeeService.lists();
        List<EmployeeDepartmentDto> employees=employeeService.getEmployeesWithDepartments();

        //返回查询到的信息到pageInfo里
//        PageInfo<Employee>pageInfo = new PageInfo<>(employees);
        PageInfo<EmployeeDepartmentDto> pageInfo = new PageInfo<>(employees);
        model.addAttribute("pageInfo", pageInfo);
        return "emplist";
    }
}
