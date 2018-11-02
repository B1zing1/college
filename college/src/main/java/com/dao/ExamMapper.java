package com.dao;

import com.bean.Department;
import com.bean.Exam;
import org.springframework.ui.ModelMap;

import java.util.List;

public interface ExamMapper {
    int insert(Exam record);

    int insertSelective(Exam record);

    //查询所有考试信息
    List<Exam> findAllExam(String examsubject);
    //根据考试id查询指定考试信息
    Exam findExamById(int examid);
    //根据考试id删除考试信息
    int deleteExamById(int examid);
    //查询指定考试信息用于修改
    Exam findExamToUpdate(int examid);
    //更新信息
    public int updateExam(Exam exam);
}