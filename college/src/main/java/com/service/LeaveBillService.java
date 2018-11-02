package com.service;

import com.bean.LeaveBill;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface LeaveBillService {

    //根据用户id查询请假单
    List<LeaveBill> findLeaveBillByUserId(int userid);
    //更改状态
    int changeState(int id, int state);
    //添加请假单
    int addLeaveBill(LeaveBill leaveBill);
    //主键查询
    LeaveBill findLeaveBillById(int id);

}
