package com.service;

import com.bean.Exam;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface ExamService {

    int insert(Exam record);

    int insertSelective(Exam record);
    //查询所有考试信息
    PageInfo findAllExam(String examsubject, int pageindex, int size);
    //根据考试id查询指定考试信息
    Exam findExamById(int examid);
    //根据考试id删除考试信息
    int deleteExamById(int examid);
    //查询所有学院
    List findAllDepartment();
    //查询指定考试信息用于修改
    Exam findExamToUpdate(int examid);
    //更新信息
    public int updateExam(Exam exam);
}
