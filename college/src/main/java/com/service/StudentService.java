package com.service;

import com.bean.Student;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface StudentService {

    int deleteByPrimaryKey(Integer studentid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //查询所有学生信息
    PageInfo findAllStudent(String stuname, String studentno, Integer stusex, int pageindex, int size);
    //查询指定学生信息
    public Student findStudentById(Student student);
    //查询所有学院
    public List findAllDepartment();
}
