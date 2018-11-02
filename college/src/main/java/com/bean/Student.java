package com.bean;

import java.util.Date;

public class Student {
    private Integer studentid;

    private String studentno;

    private String stuname;

    private Integer deptid;

    private Integer majorid;

    private Integer classid;

    private Integer stusex;

    private String email;

    private String phone;

    private String registered;

    private String address;

    private String politics;

    private String cardid;

    private String stucontent;

    private Date regdate;

    private String stustate;

    private Class class1;
    //学生对应的学院对象
    private Department department;
    //学生对应的专业对象
    private Major major;

    public Student() {
    }

    public Student(Integer studentid, String studentno, String stuname, Integer deptid, Integer majorid, Integer classid, Integer stusex, String email, String phone, String registered, String address, String politics, String cardid, String stucontent, Date regdate, String stustate, Class class1, Department department, Major major) {
        this.studentid = studentid;
        this.studentno = studentno;
        this.stuname = stuname;
        this.deptid = deptid;
        this.majorid = majorid;
        this.classid = classid;
        this.stusex = stusex;
        this.email = email;
        this.phone = phone;
        this.registered = registered;
        this.address = address;
        this.politics = politics;
        this.cardid = cardid;
        this.stucontent = stucontent;
        this.regdate = regdate;
        this.stustate = stustate;
        this.class1 = class1;
        this.department = department;
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" + "studentid=" + studentid + ", studentno='" + studentno + '\'' + ", stuname='" + stuname + '\'' + ", deptid=" + deptid + ", majorid=" + majorid + ", classid=" + classid + ", stusex=" + stusex + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", registered='" + registered + '\'' + ", address='" + address + '\'' + ", politics='" + politics + '\'' + ", cardid='" + cardid + '\'' + ", stucontent='" + stucontent + '\'' + ", regdate=" + regdate + ", stustate='" + stustate + '\'' + ", class1=" + class1 + ", department=" + department + ", major=" + major + '}';
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public String getStudentno() {
        return studentno;
    }

    public void setStudentno(String studentno) {
        this.studentno = studentno == null ? null : studentno.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
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

    public Integer getStusex() {
        return stusex;
    }

    public void setStusex(Integer stusex) {
        this.stusex = stusex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered == null ? null : registered.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics == null ? null : politics.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }

    public String getStucontent() {
        return stucontent;
    }

    public void setStucontent(String stucontent) {
        this.stucontent = stucontent == null ? null : stucontent.trim();
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getStustate() {
        return stustate;
    }

    public void setStustate(String stustate) {
        this.stustate = stustate == null ? null : stustate.trim();
    }
}