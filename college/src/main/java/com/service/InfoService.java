package com.service;

import com.bean.Information;
import com.github.pagehelper.PageInfo;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

public interface InfoService {

    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
    //查询所有资料
    PageInfo findAllInfo(String informationname, String infotype, int pageindex, int size);
    //主键查询资料信息
    Information findInfoById(Information information);
}
