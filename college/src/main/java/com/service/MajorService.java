package com.service;

import com.bean.Major;

import java.util.List;

public interface MajorService {

    int deleteByPrimaryKey(Integer majorid);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer majorid);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);
    //查询指定学院下所有专业
    List findMajorByDeptid(int departid);

}
