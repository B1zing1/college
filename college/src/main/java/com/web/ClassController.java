package com.web;

import com.bean.Class;
import com.bean.Major;
import com.bean.UserTb;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import com.service.DepartmentService;
import com.service.MajorService;
import com.service.UserService;
import com.util.ExcelUtil;
import javafx.application.Application;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ClassController {
    @Resource
    private ClassService classService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private MajorService majorService;
    @Resource
    private UserService userService;
    //查询所有班级
    @RequestMapping("/Educational/class/findAllClass")
    public String findAllClass(String classname, @RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                               @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        PageInfo pageInfo = classService.findAllClass(classname, pageindex, size, null, null);
        pageInfo.setPageSize(size);
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("classname", classname);
        return "/Educational/class/list";
    }
    //查询审核中班级信息
    @RequestMapping("/Educational/findNotClass")
    public String findNotClass(String classname, @RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                               @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        PageInfo pageInfo = classService.findAllClass(classname, pageindex, size, null, "审核中");
        modelMap.put("pageInfo", pageInfo);
        modelMap.put("classname", classname);
        modelMap.put("size", size);
        return "/Educational/Auditing";
    }
    //更改班级审核状态
    @RequestMapping("/Educational/udpateState")
    public String udpateState(Class class1){
        classService.updateByPrimaryKeySelective(class1);
        return "redirect:/Educational/findNotClass";//审核界面
    }
    //查询所有学院
    @RequestMapping("/Educational/class/findAllDepartment")
    public String findAllDepartment(ModelMap modelMap) {

        List departmentList = departmentService.findAllDepartment();
        modelMap.put("departmentList", departmentList);
        return "/Educational/class/add";
    }
    //根据学院id查询包含专业
    @RequestMapping("/Educational/class/findMajorByDeptid")
    @ResponseBody
    public List findMajorByDeptid(int departid) {

        List<Major> majorList = majorService.findMajorByDeptid(departid);
        for(int i=0; i<majorList.size(); i++) {
            List<Class> classList = classService.findClassByMajorid(majorList.get(i).getMajorid());
            majorList.get(i).setClassList(classList);
        }
        return majorList;
    }

    //查询指定专业的老师
    @RequestMapping("/Educational/class/findTeacher")
    @ResponseBody
    public List findTeacher(int departid, int majorid) {

        List teacherList = userService.findAll(departid, majorid, "班主任");
        return teacherList;
    }
    //创建班级
    @RequestMapping("/Educational/class/addClass")
    public void addClass(Class class1, HttpServletResponse response) {

        class1.setClassstate("未审核");
        int count = classService.insert(class1);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('新增班级成功');location.href = " +
                        "'/Educational/class/findAllClass'</script>");
            } else {
                printWriter.write("<script>alert('新增班级失败');top.location.href = 'add.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //提交审核
    @RequestMapping("/Educational/class/updateClassState")
    public void updateClassState(Class class1, HttpServletResponse response) {

        class1.setClassstate("审核中");
        class1.setAuditcount(class1.getAuditcount() + 1);
        int count = classService.updateByPrimaryKeySelective(class1);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('审核已提交，请等待审核');location.href = " +
                        "'/Educational/class/findAllClass'</script>");
            } else {
                printWriter.write("<script>alert('提交审核失败');location.href = " +
                        "'/Educational/class/findAllClass'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导出Excel
    @RequestMapping("/Educational/class/toExcel")
    public void toExcel(int[] single, HttpServletResponse response) {

        PageInfo pageInfo = classService.findAllClass(null, 0, 0, single, null);
        List<Class> classList = pageInfo.getList();
        String[] headers = {"院系","班级编号","班级名称","班主任老师","人数","班级状态"};
        ExcelUtil.createHead(headers);
        ExcelUtil.createOtherRow(classList);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("f:\\class"+date+".xls");
            ExcelUtil.export(out);
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert('导出成功');location.href='/Educational/class/findAllClass'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //查询指定班级信息
    @RequestMapping("/Educational/class/findClassById")
    public String findClassById(int classid, ModelMap modelMap) {

        Class class1 = classService.findClassById(classid);
        modelMap.put("class1", class1);
        return "/Educational/class/info";
    }
    //删除班级
    @RequestMapping("/Educational/class/deleteClassById")
    public void deleteClassById(int classid, HttpServletResponse response) {

        int count = classService.deleteByPrimaryKey(classid);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('删除成功');location.href = " +
                        "'/Educational/class/findAllClass'</script>");
            } else {
                printWriter.write("<script>alert('删除失败');location.href = " +
                        "'/Educational/class/findAllClass'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询班级信息用于修改
    @RequestMapping("/Educational/class/findClassToUpdate")
    public String findClassToUpdate(int classid, ModelMap modelMap) {

        Class class1 = classService.findClassById(classid);
        modelMap.put("class1", class1);
        return "/Educational/class/edit";
    }
    //更新班级信息
    @RequestMapping("/Educational/class/updateClassInfo")
    public void updateClassInfo(Class class1, HttpServletResponse response) {

        int count = classService.updateByPrimaryKeySelective(class1);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('更新成功');location.href = " +
                        "'/Educational/class/findAllClass'</script>");
            } else {
                printWriter.write("<script>alert('更新失败');location.href = " +
                        "'/Educational/class/edit.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //根据专业id查询包含的班级
    @RequestMapping("/Educational/class/findClassByMajorid")
    @ResponseBody
    public List findClassByMajorid(int majorid) {

        List<Class> classList = classService.findClassByMajorid(majorid);
        return classList;
    }
    //查询所有班级编号用于查重
    @RequestMapping("/Educational/class/checkClassNum")
    public void checkClassNum(int majorid, String classnum, HttpServletResponse response) {

        PageInfo pageInfo = classService.findAllClass(null, 0 ,0, null, null);
        List<Class> classList = pageInfo.getList();
        try {
            boolean b = false;
            PrintWriter printWriter = response.getWriter();
            for(Class c : classList) {
                if(!(c.getClassnum().equals(classnum))) {
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
    //根据学生id查询班级信息
    @RequestMapping("/Educational/class/findClassInfo")
    public String findClassInfo(ModelMap modelMap, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserTb userTb = (UserTb) session.getAttribute("userTb1");
        System.out.println("yyyyyyyyyyyyy" + userTb.getStudentId());
        Class class1 = userService.findClassInfo(userTb.getStudentId());
        modelMap.put("class1", class1);
        return "user/class";
    }


}















