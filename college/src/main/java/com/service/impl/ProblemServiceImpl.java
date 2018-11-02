package com.service.impl;

import com.bean.Problem;
import com.dao.ProblemMapper;
import com.service.ProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ProblemServiceImpl implements ProblemService {

    @Resource
    private ProblemMapper problemMapper;
    @Override
    @Transactional
    public int insertSelective(Problem record) {
        return problemMapper.insertSelective(record);
    }
}
