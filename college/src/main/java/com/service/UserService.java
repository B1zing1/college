package com.service;

import com.bean.Class;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);

    int insertSelective(UserTb record);

    UserTb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);
    //登陆
    UserTb login(UserTb userTb);
    //根据用户信息查询班级信息
    Class findClassInfo(int studentId);
    //查询
    List findAll(int departid, int majorid, String rolename);
    //查询所有用户信息包含权限
    PageInfo findAllUser(int pageindex, int size, int[] single);
    //批量删除
    int deleteUsers(int[] single);
}















