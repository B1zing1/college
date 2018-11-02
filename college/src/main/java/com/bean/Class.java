package com.bean;

import java.util.Date;

public class Class {

    private Integer classid;

    private String classnum;

    private Integer deptid;

    private Integer majorid;

    private String classname;

    private Date classbegin;

    private Date classend;

    private String classcontent;

    private String classqq;

    private String tagline;

    private String classteacher;

    private Integer peoplecount;

    private String classstate;
    //班级对应的学院对象
    private Department department;
    //班级对应的专业对象
    private Major major;
    //班级审核的次数
    private Integer auditcount;

    @Override
    public String toString() {
        return "Class{" + "classid=" + classid + ", classnum='" + classnum + '\'' + ", deptid=" + deptid + ", majorid=" + majorid + ", classname='" + classname + '\'' + ", classbegin=" + classbegin + ", classend=" + classend + ", classcontent='" + classcontent + '\'' + ", classqq='" + classqq + '\'' + ", tagline='" + tagline + '\'' + ", classteacher='" + classteacher + '\'' + ", peoplecount=" + peoplecount + ", classstate='" + classstate + '\'' + ", department=" + department + ", major=" + major + ", auditcount=" + auditcount + '}';
    }

    public Integer getAuditcount() {
        return auditcount;
    }

    public void setAuditcount(Integer auditcount) {
        this.auditcount = auditcount;
    }

    public Class(Integer classid, String classnum, Integer deptid, Integer majorid, String classname, Date classbegin, Date classend, String classcontent, String classqq, String tagline, String classteacher, Integer peoplecount, String classstate, Department department, Major major, Integer auditcount) {
        this.classid = classid;
        this.classnum = classnum;
        this.deptid = deptid;
        this.majorid = majorid;
        this.classname = classname;
        this.classbegin = classbegin;
        this.classend = classend;
        this.classcontent = classcontent;
        this.classqq = classqq;
        this.tagline = tagline;
        this.classteacher = classteacher;
        this.peoplecount = peoplecount;
        this.classstate = classstate;
        this.department = department;
        this.major = major;
        this.auditcount = auditcount;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Class() {
    }



    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassnum() {
        return classnum;
    }

    public void setClassnum(String classnum) {
        this.classnum = classnum == null ? null : classnum.trim();
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname == null ? null : classname.trim();
    }

    public Date getClassbegin() {
        return classbegin;
    }

    public void setClassbegin(Date classbegin) {
        this.classbegin = classbegin;
    }

    public Date getClassend() {
        return classend;
    }

    public void setClassend(Date classend) {
        this.classend = classend;
    }

    public String getClasscontent() {
        return classcontent;
    }

    public void setClasscontent(String classcontent) {
        this.classcontent = classcontent == null ? null : classcontent.trim();
    }

    public String getClassqq() {
        return classqq;
    }

    public void setClassqq(String classqq) {
        this.classqq = classqq == null ? null : classqq.trim();
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline == null ? null : tagline.trim();
    }

    public String getClassteacher() {
        return classteacher;
    }

    public void setClassteacher(String classteacher) {
        this.classteacher = classteacher == null ? null : classteacher.trim();
    }

    public Integer getPeoplecount() {
        return peoplecount;
    }

    public void setPeoplecount(Integer peoplecount) {
        this.peoplecount = peoplecount;
    }

    public String getClassstate() {
        return classstate;
    }

    public void setClassstate(String classstate) {
        this.classstate = classstate == null ? null : classstate.trim();
    }
}