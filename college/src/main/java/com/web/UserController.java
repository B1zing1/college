package com.web;

import com.bean.Class;
import com.bean.Menu;
import com.bean.Role;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import com.service.RoleService;
import com.service.UserService;
import com.util.ExcelUtil;
import com.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.registry.infomodel.User;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes({"logintime","userTb1","class1", "userName"})
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    //登陆
    @RequestMapping("/login")
    public void login(UserTb userTb, String imageCode, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String DropExpiration) {

        response.setContentType("text/html;charset=UTF-8");
        String code = ImageUtil.getCode();
        try {
            PrintWriter printWriter = response.getWriter();
            //用户名存入session
            modelMap.put("userName", userTb.getUserName());
            //判断验证码
            if(code.equalsIgnoreCase(imageCode)) {
                UserTb userTb1 = userService.login(userTb);
                if(userTb1 != null) {
                    //将数据库中用户信息存入modelmap放入session
                    modelMap.put("userTb1", userTb1);
                    //存入系统当前时间
                    Date logintime = new Date();
                    modelMap.put("logintime", logintime);
                    //存cookie
                    String username = request.getParameter("userName");
                    Cookie cookie = new Cookie("userName", URLEncoder.encode(username, "UTF-8"));
                    if(DropExpiration.equals("Day")) {
                        cookie.setMaxAge(24*3600);
                    } else if(DropExpiration.equals("Month")) {
                        cookie.setMaxAge(24*3600*30);
                    } else if(DropExpiration.equals("Year")) {
                        cookie.setMaxAge(24*3600*30*365);
                    }
                    response.addCookie(cookie);
                    printWriter.println("<script>alert('登陆成功');location.href='index.jsp'</script>");
                } else {
                    printWriter.println("<script>alert('用户名或密码错误');location.href='login.jsp'</script>");
                }
            } else {
                printWriter.print("<script>alert('验证码错误');location.href='login.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //退出登录
    @RequestMapping("/exit")
    public String exit(SessionStatus sessionStatus) {

        sessionStatus.setComplete();
        return "login";
    }
    //更新信息
    @RequestMapping(value="/user/updateUser", produces="text/html;charset=UTF-8")
    public void updateUser(UserTb userTb, HttpServletResponse response, ModelMap modelMap, HttpServletRequest request) {

        response.setContentType("text/html;charset=UTF-8");
        int count = userService.updateByPrimaryKeySelective(userTb);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                //从数据库中查询更新后的信息
                UserTb userTb1 = userService.selectByPrimaryKey(userTb.getUserId());
                UserTb user = userService.login(userTb1);
                Class class1 = userService.findClassInfo(userTb1.getStudentId());
                userTb1.setClass1(class1);
                modelMap.put("userTb1", user);
                printWriter.write("<script>alert('信息更新成功');top.location.href='/index.jsp'</script>");
            } else {
                printWriter.write("<script>alert('信息更新失败');location.href='MyUser.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //更新密码
    @RequestMapping("/user/updatePs")
    public void updatePs(UserTb userTb, ModelMap modelMap, HttpServletResponse response, SessionStatus sessionStatus) {

        response.setContentType("text/html;charset=UTF-8");
        int count = userService.updateByPrimaryKeySelective(userTb);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                sessionStatus.setComplete();
                printWriter.write("<script>alert('密码更新成功，请重新登录');top.location.href='/login.jsp'</script>");
            } else {
                printWriter.write("<script>alert('密码更新失败');location.href='password.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //验证密码
    @RequestMapping("/user/checkPass")
    public void  checkPass(String pass, ModelMap map, HttpServletResponse response){
        UserTb userTb = (UserTb) map.get("userTb1");
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            if(userTb.getUserPs().equals(pass)){
                out.print(true);
            }else{
                out.print(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询所有用户信息包含权限
    @RequestMapping("/power/user/findAllUser")
    public String findAllUser(@RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                              @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        PageInfo pageInfo = userService.findAllUser(pageindex, size, null);
        pageInfo.setSize(size);
        modelMap.put("pageInfo", pageInfo);
        return "/power/user/list";
    }

    //主键查询
    @RequestMapping("/power/user/findUserById")
    public String findUserById(int userId, ModelMap modelMap) {

        UserTb user = userService.selectByPrimaryKey(userId);
        modelMap.put("user", user);
        return "/power/user/info";
    }
    //删除用户
    @RequestMapping("/power/user/deleteUser")
    public void deleteUser(int userId, HttpServletResponse response) {

        int count = userService.deleteByPrimaryKey(userId);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('用户已删除');location.href='/power/user/findAllUser'</script>");
            } else {
                printWriter.write("<script>alert('删除失败，请重试');location.href='/power/user/findAllUser'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //主键查询用于修改
    @RequestMapping("/power/user/findUserToUpdate")
    public String findUserToUpdate(int userId, ModelMap modelMap) {

        UserTb user = userService.selectByPrimaryKey(userId);
        PageInfo pageInfo = roleService.findAllRole(0, 0);
        modelMap.put("user", user);
        modelMap.put("roleList", pageInfo.getList());
        return "/power/user/edit";
    }
    //更新用户
    @RequestMapping("/power/user/updateUser")
    public void updateUser(UserTb userTb, HttpServletResponse response) {

        response.setContentType("text/html;charset=UTF-8");
        int count = userService.updateByPrimaryKeySelective(userTb);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('更新成功');location.href='/power/user/findAllUser'</script>");
            } else {
                printWriter.write("<script>alert('更新失败，请重试');location.href='/power/user/findUserToUpdate'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导出Excel
    @RequestMapping("/power/user/toExcel")
    public void toExcel(int[] single, HttpServletResponse response) {

        PageInfo pageInfo = userService.findAllUser(0, 0, single);
        List<UserTb> userTbList = pageInfo.getList();
        String[] headers = {"账号","姓名","角色"};
        ExcelUtil.createHead(headers);
        ExcelUtil.createOtherRow(userTbList);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("f:\\user"+date+".xls");
            ExcelUtil.export(out);
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert('导出成功');location.href='/power/user/findAllUser'</script>");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //查询所有角色新增用户
    @RequestMapping("/power/user/findAllRole")
    public String findAllRole(ModelMap modelMap) {

        PageInfo pageInfo = roleService.findAllRole(0, 0);
        List<Role> roleList = pageInfo.getList();
        modelMap.put("roleList", roleList);
        return "/power/user/add";
    }
    //添加用户
    @RequestMapping("/power/user/addUser")
    public void addUser(UserTb userTb, HttpServletResponse response) {

        int count = userService.insertSelective(userTb);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('用户添加成功');location.href='/power/user/findAllUser'</script>");
            } else {
                printWriter.write("<script>alert('添加用户失败，请重试');location.href='/power/user/findAllRole'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //检查用户名是否重名
    @RequestMapping("/power/user/checkUserName")
    public void checkUserName(String userName, HttpServletResponse response) {

        PageInfo pageInfo = userService.findAllUser(0, 0, null);
        List<UserTb> userTbList = pageInfo.getList();
        boolean b = false;
        try {
            PrintWriter printWriter = response.getWriter();
            for (UserTb userTb : userTbList) {
                if(!(userTb.getUserName().equals(userName))) {
                    b = true;
                } else {
                    b = false;
                    break;
                }
            }
            printWriter.print(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //批量删除
    @RequestMapping("/power/user/deleteUsers")
    public void deleteUsers(int[] single, HttpServletResponse response) {

        int count = userService.deleteUsers(single);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('用户已删除');location.href='/power/user/findAllUser'</script>");
            } else {
                printWriter.write("<script>alert('删除用户失败，请重试');location.href='/power/user/findAllUser'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}












