package com.dao;

import com.bean.Major;

import java.util.List;
import java.util.Map;

public interface MajorMapper {
    int deleteByPrimaryKey(Integer majorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    //查询指定学院下所有专业
    List findMajorByDeptid(int departmentid);

}