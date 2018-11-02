package com.dao;

import com.bean.Middle;

import java.util.Map;

public interface MiddleMapper {
    int deleteByPrimaryKey(Integer middleid);

    int insert(Middle record);

    int insertSelective(Middle record);

    Middle selectByPrimaryKey(Integer middleid);

    int updateByPrimaryKeySelective(Middle record);

    int updateByPrimaryKey(Middle record);
    //根据菜单id删除记录
    int deleteMiddle(Map map);
}