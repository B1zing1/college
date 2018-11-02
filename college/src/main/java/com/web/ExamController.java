package com.web;

import com.bean.Department;
import com.bean.Exam;
import com.bean.Information;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    @Resource
    private ExamService examService;
    //查询所有考试信息
    @RequestMapping("/Educational/exam/findAllExam")
    public String findAllExam(String examsubject, @RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                              @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        System.out.println("=========="+examsubject);
        PageInfo pageInfo = examService.findAllExam(examsubject, pageindex, size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("examsubject", examsubject);
        modelMap.put("size", size);
        return "/Educational/exam/exam";
    }
    //根据考试id查询指定考试信息
    @RequestMapping("/Educational/exam/findExamById")
    public String findExamById(int examid, ModelMap modelMap) {

        Exam exam = examService.findExamById(examid);
        modelMap.put("exam", exam);
        return "/Educational/exam/view";
    }
    //根据考试id删除考试信息
    @RequestMapping("/Educational/exam/deleteExamById")
    public void deleteExamById(int examid, HttpServletResponse response) {

        int count = examService.deleteExamById(examid);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('已成功删除考试信息');location.href='/Educational/exam/findAllExam'</script>");
            } else {
                printWriter.write("<script>alert('删除失败');location.href='/Educational/exam/findAllExam'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //查询所有学院
    @RequestMapping("/Educational/exam/findAllDepartment")
    public String findAllDepartment(ModelMap modelMap) {

        List<Department> departmentList = examService.findAllDepartment();
        for(Department d : departmentList) {
            System.out.println("============"+d.getDepartname());
        }
        modelMap.put("departmentList", departmentList);
        return "/Educational/exam/add";
    }

    //根据考试id删除考试信息
    @RequestMapping("/Educational/exam/addExam")
    public void addExam(Exam exam, HttpServletResponse response) {

        int count = examService.insertSelective(exam);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('添加成功');location.href='/Educational/exam/findAllExam'</script>");
            } else {
                printWriter.write("<script>alert('添加失败');top.location.href='/Educational/exam/add.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询指定考试信息用于修改
    @RequestMapping("/Educational/exam/findExamToUpdate")
    public String findExamToUpdate(int examid, ModelMap modelMap) {

        Exam exam = examService.findExamToUpdate(examid);
        modelMap.put("exam", exam);
        return "/Educational/exam/edit";
    }
    //更新信息
    @RequestMapping("/Educational/exam/updateExam")
    public void updateExam(Exam exam, HttpServletResponse response) {

        int count = examService.updateExam(exam);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('更新成功');location.href='/Educational/exam/findAllExam'</script>");
            } else {
                printWriter.write("<script>alert('更新失败');location.href='/Educational/exam/findExamToUpdate’</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //考试编号查重
    @RequestMapping("/Educational/exam/checkExamnum")
    public void checkExamnum(String examnum, HttpServletResponse response) {

        PageInfo pageInfo = examService.findAllExam(null, 0, 0);
        List<Exam> examList = pageInfo.getList();
        try {
            boolean b = false;
            PrintWriter printWriter = response.getWriter();
            for(Exam exam : examList) {
                if(!(exam.getExamnum().equals(examnum))) {
                    b = true;
                } else {
                    b = false;
                    break;
                }
            }
            printWriter.print(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
