package com.dao;

import com.bean.LeaveBill;

import java.util.List;
import java.util.Map;

public interface LeaveBillDao {

    //根据用户id查询请假单
    List<LeaveBill> findLeaveBillByUserId(int userid);
    //更改状态
    int changeState(Map map);
    //添加请假单
    int addLeaveBill(LeaveBill leaveBill);
    //主键查询
    LeaveBill findLeaveBillById(int id);
}
