package com.service.impl;

import com.bean.LeaveBill;
import com.bean.UserTb;
import com.dao.LeaveBillDao;
import com.service.ActivitiService;
import com.service.LeaveBillService;
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class ActivitiServiceImpl implements ActivitiService {

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private LeaveBillService leaveBillService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private FormService formService;

    @Override
    public List<Deployment> findAllDeploys() {

        List<Deployment> deploymentList = repositoryService.createDeploymentQuery().list();
        return deploymentList;
    }

    @Override
    public List<ProcessDefinition> findAllProcess() {

        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().list();
        return processDefinitionList;
    }

    @Override
    public int deploy(InputStream in, String name) {

        ZipInputStream zipInputStream = new ZipInputStream(in);
        repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name(name)
                .deploy();
        return 1;
    }

    @Override
    @Transactional
    public int delete(String id) {

        repositoryService.deleteDeployment(id);
        return 1;
    }


    @Override
    public InputStream findImage(String deploymentId, String imageName) {
        return repositoryService.getResourceAsStream(deploymentId,imageName);
    }

    @Override
    public void submit(int id, String username) {

        //修改请假状态
        int count = leaveBillService.changeState(id, 1);
        /*
        启动流程，需建立流程和业务之间的关系
        使用business_key来保存业务信息
        business_key：流程key.业务id
         */
        String business = "LeaveBill." + id;
        Map map = new HashMap();
        map.put("uname", username);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("LeaveBill", business, map);
        //根据流程实例id查询任务对象
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
    }

    @Override
    public List<Task> findByUserName(String userName) {
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(userName).list();
        return taskList;
    }

    @Override
    public String getFormKey(String taskid) {
        TaskFormData taskFormData = formService.getTaskFormData(taskid);
        return taskFormData.getFormKey();
    }

    @Override
    public LeaveBill findLeaveBillByTaskid(String taskid) {
        //通过任务id查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        //通过任务对象得到流程实例id
        String processInstanceId = task.getProcessInstanceId();
        //通过流程实例id查询流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //通过流程实例对象得到business_key
        String businessKey = processInstance.getBusinessKey();
        //截取请假单id
        String leaveBillId = businessKey.substring(businessKey.indexOf(".") + 1);
        //通过id查询请假单对象
        LeaveBill leaveBill = leaveBillService.findLeaveBillById(Integer.valueOf(leaveBillId));

        return leaveBill;
    }

    @Override
    public List getNames(String taskid) {

        //通过任务id查询任务对象
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        //得到流程定义id
        String processDefinitionId = task.getProcessDefinitionId();
        //通过流程定义id得到是流程图对象
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        //根据acyiviti_id得到当前正在活动的对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        String activityId = processInstance.getActivityId();
        ActivityImpl activity = processDefinitionEntity.findActivity(activityId);
        List<PvmTransition> outgoingTransitionsList = activity.getOutgoingTransitions();
        List<String> nameList = new ArrayList();
        if(outgoingTransitionsList.size() > 0) {
            for(PvmTransition pvmTransition : outgoingTransitionsList) {
                String name = (String) pvmTransition.getProperty("name");
                nameList.add(name);
            }
        }
        return nameList;
    }

    @Override
    public void chuli(String pizhu, String taskid, int leaveBillid, String result, String userName) {

        //添加批注信息表
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        Authentication.setAuthenticatedUserId(userName); //设置comment表中的userid
        taskService.addComment(taskid, task.getProcessInstanceId(), pizhu);
        //设置流程变量的值
        Map map = new HashMap();
        map.put("rs", result);
        //完成个人任务
        taskService.complete(taskid, map);
        //判断任务是否执行完毕
        //查询流程实例对象
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        if(processInstance == null) {
            leaveBillService.changeState(leaveBillid, 2);
        }
        if(result.equals("驳回")) {
            //删除流程实例
            runtimeService.deleteProcessInstance(task.getProcessInstanceId(), "驳回");
            //修改请假状态
            leaveBillService.changeState(leaveBillid, 3);
        }
    }

    @Override
    public List getComments(String taskid) {

        List commentList = new ArrayList();
        //得到任务的流程实例id
        Task task = taskService.createTaskQuery().taskId(taskid).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        //通过流程实例id查询该id对应的历史任务
        commentList = taskService.getProcessInstanceComments(processInstanceId);
        return commentList;
    }

}


















