package com.web;

import com.bean.LeaveBill;
import com.bean.UserTb;
import com.service.LeaveBillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class LeaveBillController {

    @Resource
    private LeaveBillService leaveBillService;
    //根据用户id查询请假单
    @RequestMapping("/qingjia/findLeaveBillByUserId")
    public String findLeaveBillByUserId(ModelMap modelMap, HttpSession session) {

        UserTb userTb = (UserTb) session.getAttribute("userTb1");
        List<LeaveBill> leaveBillList = leaveBillService.findLeaveBillByUserId(userTb.getUserId());
        modelMap.put("leaveBillList", leaveBillList);
        return "/qingjia/list";
    }
    //添加请假单
    @RequestMapping("/qingjia/addLeaveBill")
    public void addLeaveBill(LeaveBill leaveBill, HttpServletResponse response) {

        int count = leaveBillService.addLeaveBill(leaveBill);
        try {
            if(count > 0) {
                response.getWriter().print("<script>alert('请假信息已提交');location.href='/qingjia/findLeaveBillByUserId'</script>");
            } else {
                response.getWriter().print("<script>alert('请假信息提交失败');location.href='/qingjia/add.jsp'</script>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
