package com.service.impl;

import com.bean.Information;
import com.dao.InformationMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.InfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InfoServiceImpl implements InfoService {

    @Resource
    private InformationMapper informationMapper;

    @Override
    public int deleteByPrimaryKey(Integer informationid) {
        return 0;
    }

    @Override
    public int insert(Information record) {
        return 0;
    }

    @Override
    @Transactional
    public int insertSelective(Information record) {
        return informationMapper.insertSelective(record);
    }

    @Override
    public Information selectByPrimaryKey(Integer informationid) {
        return informationMapper.selectByPrimaryKey(informationid);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Information record) {
        return informationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(Information record) {
        return 0;
    }

    @Override
    public PageInfo findAllInfo(String informationname, String infotype, int pageindex, int size) {

        Map map = new HashMap();
        map.put("informationname", informationname);
        map.put("infotype", infotype);
        //显示页码和显示条数
        PageHelper.startPage(pageindex, size);
        List list = informationMapper.findAllInfo(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Information findInfoById(Information information) {

        Map map = new HashMap();
        map.put("informationid", information.getInformationid());
        Information information1 = informationMapper.findInfoById(map);
        return information1;
    }
}
