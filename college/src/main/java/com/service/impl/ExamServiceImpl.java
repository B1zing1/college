package com.service.impl;

import com.bean.Exam;
import com.dao.DepartmentMapper;
import com.dao.ExamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {

    @Resource
    private ExamMapper examMapper;
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public int insert(Exam record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Exam record) {
        return examMapper.insertSelective(record);
    }

    @Override
    public PageInfo findAllExam(String examsubject, int pageindex, int size) {

        //显示页码和显示条数
        PageHelper.startPage(pageindex, size);
        List list = examMapper.findAllExam(examsubject);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    //根据考试id查询指定考试信息
    @Override
    public Exam findExamById(int examid) {
        return examMapper.findExamById(examid);
    }

    @Override
    @Transactional
    public int deleteExamById(int examid) {
        return examMapper.deleteExamById(examid);
    }

    @Override
    public List findAllDepartment() {
        return departmentMapper.findAllDepartment();
    }

    @Override
    public Exam findExamToUpdate(int examid) {
        return examMapper.findExamToUpdate(examid);
    }

    @Override
    @Transactional
    public int updateExam(Exam exam) {
        return examMapper.updateExam(exam);
    }
}
