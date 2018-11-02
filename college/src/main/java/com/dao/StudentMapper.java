package com.dao;

import com.bean.Student;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface StudentMapper {
    int deleteByPrimaryKey(Integer studentid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    //查询所有学生信息
    List findAllStudent(Map map);
    //查询指定学生信息
    public Student findStudentById(Student student);
    //查询所有学院
    public List findAllDepartment();
}