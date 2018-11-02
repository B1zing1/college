package com.web;

import com.bean.Problem;
import com.service.ProblemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@SessionAttributes("problemContent")
public class ProblemController {

    @Resource
    private ProblemService problemService;
    //添加学生意见
    @RequestMapping("/Educational/problem/addProblem")
    public void addProblem(Problem problem, HttpServletResponse response, ModelMap modelMap, HttpServletRequest request) {

        response.setContentType("text/html;charset=UTF-8");
        int count = problemService.insertSelective(problem);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                modelMap.remove("problemContent");
                HttpSession session = request.getSession();
                session.removeAttribute("problemContent");
                printWriter.write("<script>alert('" + problem.getProblemtype() + "已提交');location.href = '/user/class.jsp'</script>");
            } else {
                modelMap.put("problemContent", problem.getProblemcontent());
                printWriter.write("<script>alert('" + problem.getProblemtype() + "提交失败');location.href = '/user/class.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}













