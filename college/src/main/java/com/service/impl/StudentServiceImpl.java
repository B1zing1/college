package com.service.impl;

import com.bean.Student;
import com.dao.DepartmentMapper;
import com.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.DepartmentService;
import com.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer studentid) {

        return studentMapper.deleteByPrimaryKey(studentid);
    }

    @Override
    public int insert(Student record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Student record) {

        return studentMapper.insertSelective(record);
    }

    @Override
    public Student selectByPrimaryKey(Integer studentid) {
        return null;
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Student record) {
        int count = studentMapper.updateByPrimaryKeySelective(record);
        return count;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }

    @Override
    public PageInfo findAllStudent(String stuname, String studentno, Integer stusex, int pageindex, int size) {
        Map map = new HashMap();
        map.put("stuname", stuname);
        map.put("studentno", studentno);
        map.put("stusex", stusex);
        map.put("pageindex", pageindex);
        map.put("size", size);
        //显示页码和显示条数
        PageHelper.startPage(pageindex, size);
        List list = studentMapper.findAllStudent(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    //查询指定学生信息
    public Student findStudentById(Student student) {

        Student student1 = studentMapper.findStudentById(student);
        return student1;
    }

    //查询所有学院
    public List findAllDepartment() {
        return departmentMapper.findAllDepartment();
    }
}
