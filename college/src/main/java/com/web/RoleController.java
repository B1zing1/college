package com.web;

import com.bean.Menu;
import com.bean.Middle;
import com.bean.Role;
import com.dao.MenuMapper;
import com.dao.MiddleMapper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    //查询全部角色
    @RequestMapping("/power/role/findAllRole")
    public String findAllRole(@RequestParam(value = "pageindex", defaultValue = "1") int pageindex,
                              @RequestParam(value = "size", defaultValue = "3") int size, ModelMap modelMap) {

        PageInfo pageInfo = roleService.findAllRole(pageindex, size);
        pageInfo.setPageSize(size);
        List<Role> list = pageInfo.getList();
        modelMap.put("pageInfo", pageInfo);
        return "/power/role/list";
    }
    //更改角色状态
    @RequestMapping("/power/role/changeState")
    public void changeState(Role role, HttpServletResponse response) {

        int  count = roleService.changeState(role);
        try {
            PrintWriter printWriter = response.getWriter();
            String s = "";
            if(role.getRolestate() == 1) {
                s = "启用";
            } else if(role.getRolestate() == 0) {
                s = "禁用";
            }
            if(count > 0) {
                printWriter.write("<script>alert('"+s+"成功');location.href='/power/role/findAllRole'</script>");
            } else {
                printWriter.write("<script>alert('"+s+"失败');location.href='/power/role/findAllRole'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //主键查询角色信息
    @RequestMapping("/power/role/findRoleById")
    public String findRoleById(int roleid, ModelMap modelMap) {

        Role role = roleService.findRoleById(roleid);
        modelMap.put("role", role);
        return "/power/role/info";
    }
    //主键查询用于修改
    @RequestMapping("/power/role/findRoleToUpdate")
    public String findRoleToUpdate(int roleid, ModelMap modelMap) {

        Role role = roleService.findRoleToUpdate(roleid);
        PageInfo pageInfo = roleService.findAllRole(0, 0);
        List<Menu> menuList = menuService.findMenuByRoleId(roleid);
        List<Role> roleList = pageInfo.getList();
        modelMap.put("roleList", roleList);
        modelMap.put("role", role);
        modelMap.put("menuList", menuList);
        return "/power/role/edit";
    }
    //修改权限
    @RequestMapping("/power/role/updateRole")
    public void updateRole(Role role, int[] menus, HttpServletResponse response) {

        int count = roleService.updateByPrimaryKeySelective(role);
        int count2 = roleService.deleteMiddleByRoleId(role.getRoleid());
        int count3 = roleService.updateMiddle(role.getRoleid(), menus);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count3 > 0) {
                printWriter.write("<script>alert('更新成功');location.href='/power/role/findAllRole'</script>");
            } else {
                printWriter.write("<script>alert('更新失败');location.href='/power/role/findRoleToUpdate'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //删除角色
    @RequestMapping("/power/role/deleteRole")
    public void deleteRole(int roleid, HttpServletResponse response) {

        int count = roleService.deleteByPrimaryKey(roleid);
        System.out.println("------------" + count);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.print("<script>alert('角色已删除');location.href='/power/role/findAllRole'</script>");
            } else {
                printWriter.print("<script>alert('删除失败，该角色正被用户使用');location.href='/power/role/findAllRole'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询所有菜单
    @RequestMapping("/power/role/findAllMenu")
    public String findAllMenu(ModelMap modelMap) {

        List<Menu> menuList = roleService.findAllMenu();
        modelMap.put("menuList", menuList);
        return "/power/role/add";
    }
    //添加角色
    @RequestMapping("/power/role/addRole")
    public void addRole(Role role, int[] menus, HttpServletResponse response) {
        int count = roleService.addRole(role, menus);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('新增角色成功');location.href='/power/role/findAllRole'</script>");
            } else {
                printWriter.write("<script>alert('新增角色失败');location.href='/power/role/findAllMenu'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //判断新增角色名是否已存在
    @RequestMapping("/power/role/checkRoleName")
    public void checkRoleName(String rolename, String rname, HttpServletResponse response) {

        PageInfo pageInfo = roleService.findAllRole(0, 0);
        List<Role> roleLiist = pageInfo.getList();
        try {
            boolean b = false;
            PrintWriter printWriter = response.getWriter();
            for (Role role : roleLiist) {
                if(!(role.getRolename().equals(rolename))) {
                    b = true;
                } else {
                    b = false;
                    break;
                }
            }
            if(rname != null && rolename.equals(rname)) {
                b = true;
            }
            printWriter.print(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}












