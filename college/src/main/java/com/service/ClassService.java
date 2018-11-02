package com.service;

import com.bean.Class;
import com.bean.Major;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ClassService {

    int deleteByPrimaryKey(Integer classid);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);

    PageInfo findAllClass(String classname, int pageindex, int size, int[] classids, String classstate);
    //查询指定班级信息
    Class findClassById(int classid);
    //根据专业id查询包含的班级
    List<Class> findClassByMajorid(int majorid);

}
