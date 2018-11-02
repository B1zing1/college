package com.web;

import com.bean.Department;
import com.bean.Student;
import com.github.pagehelper.PageInfo;
import com.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class StudentController {

    @Resource
    private StudentService studentService;

    @RequestMapping("/Educational/student/findAllStudent")
    //查询所有学生信息
    public String findAllStudent(String stuname, String studentno, Integer stusex, @RequestParam(value = "pageindex", defaultValue = "1") int pageindex, @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        PageInfo pageInfo = studentService.findAllStudent(stuname, studentno, stusex, pageindex, size);
        pageInfo.setPageSize(size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("stuname", stuname);
        modelMap.put("studentno", studentno);
        modelMap.put("stusex", stusex);
        return "/Educational/student/list";
    }

    @RequestMapping("/Educational/student/findStudentById")
    //查询指定学生信息
    public String findStudentById(Student student, ModelMap modelMap) {

        Student student1 = studentService.findStudentById(student);
        modelMap.put("student1", student1);
        return "/Educational/student/view";
    }

    @RequestMapping("/Educational/student/findStudentToUpdate")
    //查询指定学生信息用于修改
    public String findStudentToUpdate(Student student, ModelMap modelMap) {

        Student student1 = studentService.findStudentById(student);
        System.out.println("=====~========" + student1.getDepartment().getDepartid());
        System.out.println("=====~========" + student1.getClass1().getClassid());
        modelMap.put("student1", student1);
        return "/Educational/student/edit";
    }

    //修改学生信息
    @RequestMapping("/Educational/student/updateStudentById")
    public void updateStudentById(Student student, HttpServletResponse response) {
        System.out.println("===========" + student);
        int count = studentService.updateByPrimaryKeySelective(student);
        try {
            PrintWriter printWriter = response.getWriter();
            if (count > 0) {
                printWriter.write("<script>alert('修改成功');location.href='/Educational/student/findAllStudent'</script>");
            } else {
                printWriter.write("<script>alert('修改失败');location.href='/Educational/student/findStudentToUpdate?studentid=" + student.getStudentid() + "'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //添加学生
    @RequestMapping("/Educational/student/addStudent")
    public void addStudent(Student student, HttpServletResponse response) {
        System.out.println("===========" + student);
        student.setRegdate(new Date());
        int count = studentService.insertSelective(student);
        try {
            PrintWriter printWriter = response.getWriter();
            if (count > 0) {
                printWriter.write("<script>alert('添加成功');location.href='/Educational/student/findAllStudent'</script>");
            } else {
                printWriter.write("<script>alert('添加失败');location.href=/Educational/student/add.jsp=</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询所有学院
    @RequestMapping("/Educational/student/findAllDepartment")
    public String findAllDepartment(ModelMap modelMap) {

        List<Department> departmentList = studentService.findAllDepartment();
        for(Department d : departmentList) {
            System.out.println("============"+d.getDepartname());
        }
        modelMap.put("departmentList", departmentList);
        return "/Educational/student/add";
    }
    //删除学生
    @RequestMapping("/Educational/student/deleteStudent")
    public void deleteStudent(int studentid, HttpServletResponse response) {
        System.out.println("===========" + studentid);
        int count = studentService.deleteByPrimaryKey(studentid);
        try {
            PrintWriter printWriter = response.getWriter();
            if (count > 0) {
                printWriter.write("<script>alert('退学成功');location.href='/Educational/student/findAllStudent'</script>");
            } else {
                printWriter.write("<script>alert('退学失败');location.href='/Educational/student/findAllStudent'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询所i有学生编号用于新增查重
    @RequestMapping("/Educational/student/checkStudentno")
    public void checkStudentno(String studentno, HttpServletResponse response) {
        PageInfo pageInfo = studentService.findAllStudent(null, studentno, null, 0, 0);
        List<Student> studentList = pageInfo.getList();
        try {
            PrintWriter printWriter = response.getWriter();
            for(Student student : studentList) {
                if(student.getStudentno().equals(studentno)) {
                    printWriter.print(false);
                } else {
                    printWriter.print(true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
