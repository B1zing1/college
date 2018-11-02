package com.dao;

import com.bean.Information;

import java.util.List;
import java.util.Map;

public interface InformationMapper {
    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    //查询所有资料
    List findAllInfo(Map map);
    //主键查询资料信息
    Information findInfoById(Map map);
}