package com.web;

import com.bean.LeaveBill;
import com.bean.UserTb;
import com.service.ActivitiService;
import com.service.LeaveBillService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
public class ActivitiController {

    @Resource
    private ActivitiService activitiService;
    @Resource
    private LeaveBillService leaveBillService;

    //查询流程定义和部署信息
    @RequestMapping("/bushu/findAllDeploys")
    public String findAllDeploys(ModelMap modelMap) {
        //查询部署信息
        List<Deployment> deploymentList = activitiService.findAllDeploys();
        //查询流程定义信息
        List<ProcessDefinition> processDefinitionList = activitiService.findAllProcess();
        modelMap.put("deploymentList", deploymentList);
        modelMap.put("processDefinitionList", processDefinitionList);
        return "/bushu/list";
    }
    //上传流程文件
    @RequestMapping("/bushu/deploy")
    public String deploy(MultipartFile multipartFile, String name) {

        try {
            activitiService.deploy(multipartFile.getInputStream(), name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/bushu/findAllDeploys";
    }
    //删除流程
    @RequestMapping("/bushu/delete")
    public void delete(String id, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            activitiService.delete(id);
            response.getWriter().print("<script>alert('删除成功');location.href='/bushu/findAllDeploys'<script>");
        } catch(Exception e) {
            try {
                response.getWriter().print("<script>alert('流程正在进行中，不能删除');location.href='/bushu/findAllDeploys'<script>");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    //查看流程图
    @RequestMapping("/bushu/lookImage")
    public void lookImage(String deploymentId, String imageName, HttpServletResponse response) {

        try {
            //根据部署id和图片名称，查看文件流
            InputStream inputStream=activitiService.findImage(deploymentId,imageName);
            //将文件保存到本地
            FileUtils.copyInputStreamToFile(inputStream,new File("e://"+imageName));
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script>alert('文件保存在e盘,文件名:"+imageName+"');location.href='/bushu/findAllDeploys'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //提交审核
    @RequestMapping("/qingjia/submit")
    public void submit(int leaveid, HttpServletResponse response, HttpSession session) {

        UserTb userTb = (UserTb) session.getAttribute("userTb1");
        int count = leaveBillService.changeState(leaveid, 1);
        activitiService.submit(leaveid, userTb.getUserName());
        try {
            if(count > 0) {
                response.getWriter().print("<script>alert('审核已提交');location.href='/qingjia/findLeaveBillByUserId'</script>");
            } else {
                response.getWriter().print("<script>alert('审核提交失败');location.href='/qingjia/findLeaveBillByUserId'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //根据用户id查询任务
    @RequestMapping("/renwu/findByUserName")
    public String findByUserName(ModelMap modelMap, HttpSession session) {

        UserTb userTb = (UserTb) session.getAttribute("userTb1");
        List<Task> taskList = activitiService.findByUserName(userTb.getUserName());
        modelMap.put("taskList", taskList);
        return "/renwu/list";
    }
    //查询form_id
    @RequestMapping("/renwu/banli")
    public String getFormKey(String taskid) {

        //使用form_key执行流程请求
        String url = activitiService.getFormKey(taskid);
        return "redirect:/" + url + "?taskid=" + taskid;
    }
    //查看任务
    @RequestMapping("/shenpi")
    public String banli(String taskid, ModelMap modelMap) {

        //通过任务id查询请假条
        LeaveBill leaveBill = activitiService.findLeaveBillByTaskid(taskid);
        modelMap.put("leaveBill", leaveBill);
        //通过任务id查询正在活动的节点对象，并获取任务连线信息
        List<String> nameList = activitiService.getNames(taskid);
        modelMap.put("nameList", nameList);
        modelMap.put("taskid", taskid);
        //得到任务批注信息
        List<Comment> commentList = activitiService.getComments(taskid);
        modelMap.put("commentList", commentList);
        return "/renwu/banli";
    }
    //办理任务
    @RequestMapping("/renwu/shenpi")
    public String chuli(String pizhu, String taskid, int leaveBillid, String result, HttpSession session) {

        UserTb userTb = (UserTb) session.getAttribute("userTb1");
        activitiService.chuli(pizhu, taskid, leaveBillid, result, userTb.getUserName());
        return "redirect:/renwu/findByUserName";
    }
}

















