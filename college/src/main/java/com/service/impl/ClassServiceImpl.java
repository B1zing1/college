package com.service.impl;

import com.bean.Class;
import com.bean.Major;
import com.dao.ClassMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {
    @Resource
    private ClassMapper classMapper;

    @Override
    @Transactional
    public int deleteByPrimaryKey(Integer classid) {
        return classMapper.deleteByPrimaryKey(classid);
    }

    @Override
    @Transactional
    public int insert(Class record) {
        return classMapper.insert(record);
    }

    @Override
    public int insertSelective(Class record) {
        return 0;
    }

    @Override
    public Class selectByPrimaryKey(Integer classid) {
        return classMapper.selectByPrimaryKey(classid);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Class record) {
        return classMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Class record) {
        return 0;
    }

    @Override
    public PageInfo findAllClass(String classname, int pageindex, int size, int[] classids, String classstate) {

        Map map = new HashMap();
        map.put("classname", classname);
        map.put("classids", classids);
        map.put("classstate", classstate);
        //显示页码和显示条数
        PageHelper.startPage(pageindex, size);
        List list = classMapper.findAllClass(map);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public Class findClassById(int classid) {
        return classMapper.findClassById(classid);
    }

    @Override
    public List<Class> findClassByMajorid(int majorid) {
        return classMapper.findClassByMajorid(majorid);
    }


}










