package com.util;

import com.bean.UserTb;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.http.*;

public class LeaveBillListener implements TaskListener/*, HttpSessionListener*/ {

    private UserTb userTb;

    @Override
    public void notify(DelegateTask delegateTask) {
        HttpSession session=((ServletRequestAttributes)
                (RequestContextHolder.getRequestAttributes())).getRequest().getSession();
        UserTb userTb = (UserTb) session.getAttribute("userTb1");
        UserTb manager = userTb.getManager();
        delegateTask.setAssignee(manager.getUserName());
    }


   /*@Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        userTb = (UserTb) httpSessionEvent.getSession().getAttribute("userTb1");
       System.out.println("//////////////"+httpSessionEvent.getSession());
       *//*System.out.println("................."+httpSessionEvent.getSession().getAttribute("userTb1"));
       System.out.println("................."+httpSessionEvent.getSession().getAttribute("class1"));
       System.out.println("................."+httpSessionEvent.getSession().getAttribute("logintime"));
       System.out.println("................."+httpSessionEvent.getSession().getAttribute("userName"));*//*
       System.out.println("+++++++++++" + userTb);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }*/
}
