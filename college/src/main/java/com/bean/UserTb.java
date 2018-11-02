package com.bean;

import java.util.Date;

public class UserTb {
    private Integer userId;

    private Integer studentId;

    private Integer roleId;

    private String userName;

    private String userPs;

    private String userRealname;

    private String userSex;

    private String userEmail;

    private String userPhone;

    private String userAddress;

    private String userIdcard;

    private String userContent;

    private Integer logincount;

    private Date regdate;
    //用户所在班级对象
    private Class class1;
    //用户对应角色对象
    private Role role;
    //上级id
    private int managerid;

    private UserTb manager;

    public UserTb getManager() {
        return manager;
    }

    public void setManager(UserTb manager) {
        this.manager = manager;
    }

    public int getManagerid() {
        return managerid;
    }

    public void setManagerid(int managerid) {
        this.managerid = managerid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserTb() {
    }

    @Override
    public String toString() {
        return "UserTb{" + "userId=" + userId + ", studentId=" + studentId + ", roleId=" + roleId + ", userName='" + userName + '\'' + ", userPs='" + userPs + '\'' + ", userRealname='" + userRealname + '\'' + ", userSex='" + userSex + '\'' + ", userEmail='" + userEmail + '\'' + ", userPhone='" + userPhone + '\'' + ", userAddress='" + userAddress + '\'' + ", userIdcard='" + userIdcard + '\'' + ", userContent='" + userContent + '\'' + ", logincount=" + logincount + ", regdate=" + regdate + ", class1=" + class1 + ", role=" + role + ", managerid=" + managerid + '}';
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPs() {
        return userPs;
    }

    public void setUserPs(String userPs) {
        this.userPs = userPs == null ? null : userPs.trim();
    }

    public String getUserRealname() {
        return userRealname;
    }

    public void setUserRealname(String userRealname) {
        this.userRealname = userRealname == null ? null : userRealname.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard == null ? null : userIdcard.trim();
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent == null ? null : userContent.trim();
    }

    public Integer getLogincount() {
        return logincount;
    }

    public void setLogincount(Integer logincount) {
        this.logincount = logincount;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }
}