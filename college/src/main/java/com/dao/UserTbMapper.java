package com.dao;

import com.bean.Class;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserTbMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);

    int insertSelective(UserTb record);
    //主键查询
    UserTb selectByPrimaryKey(Integer userId);
    //动态更新
    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);
    //登陆
    UserTb login(String userName);
    //根据用户信息查询班级信息
    Class findClassInfo(int studentId);
    //查询
    List findAll(Map map);
    //查询所有用户信息包含权限
    List findAllUser(Map map);
    //批量删除
    int deleteUsers(Map map);
}

















