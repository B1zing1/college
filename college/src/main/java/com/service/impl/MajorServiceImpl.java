package com.service.impl;

import com.bean.Major;
import com.dao.MajorMapper;
import com.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MajorServiceImpl implements MajorService {

    @Resource
    private MajorMapper majorMapper;

    @Override
    public int deleteByPrimaryKey(Integer majorid) {
        return 0;
    }

    @Override
    public int insert(Major record) {
        return 0;
    }

    @Override
    public int insertSelective(Major record) {
        return 0;
    }

    @Override
    public Major selectByPrimaryKey(Integer majorid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Major record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Major record) {
        return 0;
    }

    @Override
    public List findMajorByDeptid(int departid) {
        return majorMapper.findMajorByDeptid(departid);
    }

}
