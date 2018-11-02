package com.service;

import com.bean.LeaveBill;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.List;

public interface ActivitiService {

    //查询流程定义和部署信息
    List<Deployment> findAllDeploys();
    //查询流程定义信息
    List<ProcessDefinition> findAllProcess();
    //新增流程部署
    int deploy(InputStream in, String name);
    //删除流程
    int delete(String id);
    //查看流程图
    InputStream findImage(String deploymentId, String imageName);
    //提交审核
    void submit(int id, String username);
    //根据用户id查询任务
    List<Task> findByUserName(String userName);
    //查询
    String getFormKey(String taskid);
    //根据任务id查询请假条
    LeaveBill findLeaveBillByTaskid(String taskid);
    //根据任务id查询正在活动的连线信息
    List getNames(String taskid);
    //办理业务
    void chuli(String pizhu, String taskid, int leaveBillid, String result, String userName);
    //得到批注信息
    List getComments(String taskid);
}














