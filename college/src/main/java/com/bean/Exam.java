package com.bean;

import java.util.Date;

public class Exam {
    private Integer examid;

    private String examnum;

    private String examsubject;

    private Date examtime;

    private Integer deptid;

    private Integer majorid;

    private Integer classid;

    private Integer examcount;

    private String examstate;
    //考试对应的班级对象
    private Class class1;
    //考试对应的学院对象
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getExamsubject() {
        return examsubject;
    }

    public void setExamsubject(String examsubject) {
        this.examsubject = examsubject;
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    public Integer getExamid() {
        return examid;
    }

    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    public String getExamnum() {
        return examnum;
    }

    public void setExamnum(String examnum) {
        this.examnum = examnum == null ? null : examnum.trim();
    }

    /*public String getExamsubject() {
        return examsubject;
    }

    public void setExamsubject(String examsubject) {
        this.examsubject = examsubject == null ? null : examsubject.trim();
    }*/

    public Date getExamtime() {
        return examtime;
    }

    public void setExamtime(Date examtime) {
        this.examtime = examtime;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public Integer getMajorid() {
        return majorid;
    }

    public void setMajorid(Integer majorid) {
        this.majorid = majorid;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getExamcount() {
        return examcount;
    }

    public void setExamcount(Integer examcount) {
        this.examcount = examcount;
    }

    public String getExamstate() {
        return examstate;
    }

    public void setExamstate(String examstate) {
        this.examstate = examstate == null ? null : examstate.trim();
    }
}