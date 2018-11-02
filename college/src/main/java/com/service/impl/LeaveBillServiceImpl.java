package com.service.impl;

import com.bean.LeaveBill;
import com.dao.LeaveBillDao;
import com.service.LeaveBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveBillServiceImpl implements LeaveBillService {

    @Resource
    private LeaveBillDao leaveBillDao;

    @Override
    public List<LeaveBill> findLeaveBillByUserId(int userid) {
        return leaveBillDao.findLeaveBillByUserId(userid);
    }

    @Override
    @Transactional
    public int changeState(int id, int state) {
        Map map = new HashMap();
        map.put("id", id);
        map.put("state", state);
        return leaveBillDao.changeState(map);
    }

    @Override
    @Transactional
    public int addLeaveBill(LeaveBill leaveBill) {
        return leaveBillDao.addLeaveBill(leaveBill);
    }

    @Override
    public LeaveBill findLeaveBillById(int id) {
        return leaveBillDao.findLeaveBillById(id);
    }


}
