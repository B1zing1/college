package com.web;

import com.bean.Class;
import com.bean.Menu;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.util.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MenuController {

    @Resource
    private MenuService menuService;

    //查询所有菜单
    @RequestMapping("/power/menu/findAllMenu")
    public String findAllMenu(@RequestParam(value = "pageindex",defaultValue = "1") int pageindex,
                              @RequestParam(value = "size",defaultValue = "5") int size, ModelMap modelMap) {

        PageInfo pageInfo = menuService.findMenu(pageindex, size, null);
        pageInfo.setSize(size);
        modelMap.put("pageInfo", pageInfo);
        return "/power/menu/list";
    }
    //主键查询菜单信息
    @RequestMapping("/power/menu/findMenuById")
    public String findMenuById(int menuid, ModelMap modelMap) {

        Menu menu = menuService.selectByPrimaryKey(menuid);
        if(menu.getUpmenuid() != -1) {
            Menu topMenu = menuService.findTopMenu(menu.getUpmenuid());
            modelMap.put("topMenu", topMenu);
        }
        modelMap.put("menu", menu);
        return "/power/menu/info";
    }
    //查询所有顶级菜单
    @RequestMapping("/power/menu/findAllTopMenu")
    public String findTopMenu(ModelMap modelMap) {

        List<Menu> menuList = menuService.findAllTopMenu();
        modelMap.put("menuList", menuList);
        return "/power/menu/add";
    }
    //添加菜单
    @RequestMapping("/power/menu/addMenu")
    public void addMenu(Menu menu, HttpServletResponse response) {

        int count = menuService.insertSelective(menu);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('新增菜单成功');location.href = " +
                        "'/power/menu/findAllMenu'</script>");
            } else {
                printWriter.write("<script>alert('新增菜单失败');location.href = '/power/menu/findAllTopMenu'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //查询菜单信息用于修改
    @RequestMapping("/power/menu/findMenuToUpdate")
    public String findMenuToUpdate(int menuid, ModelMap modelMap) {

        Menu menu = menuService.selectByPrimaryKey(menuid);
        List<Menu> menuList = menuService.findAllTopMenu();
        modelMap.put("menuList", menuList);
        modelMap.put("menu", menu);
        return "/power/menu/edit";
    }
    //修改菜单信息
    @RequestMapping("/power/menu/updateMenu")
    public void updateMenu(Menu menu, HttpServletResponse response) {

        int count = menuService.updateByPrimaryKeySelective(menu);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('菜单修改成功');location.href = " +
                        "'/power/menu/findAllMenu'</script>");
            } else {
                printWriter.write("<script>alert('菜单修改失败');location.href = '/power/menu/findMenuToUpdate?menuid='"+menu.getMenuid()+"</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导出Excel
    @RequestMapping("/power/menu/toExcel")
    public void toExcel(String[] single, HttpServletResponse response) {

        List<Integer> menuidList = new ArrayList<>();
        for (String s : single) {
            int index = s.indexOf("-");
            int mid=Integer.parseInt(s.substring(0,index));
        }
        PageInfo pageInfo = menuService.findMenu(0, 0, menuidList);
        List<Menu> menuList = pageInfo.getList();
        String[] headers = {"菜单名称","UTL","状态"};
        ExcelUtil.createHead(headers);
        ExcelUtil.createOtherRow(menuList);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("f:\\menu"+date+".xls");
            ExcelUtil.export(out);
            PrintWriter printWriter = response.getWriter();
            printWriter.print("<script>alert('导出成功');location.href='/power/menu/findAllMenu'</script>");
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
    //菜单名查重
    @RequestMapping("/power/menu/checkMenuName")
    public void checkMenuName(String menuname, HttpServletResponse response) {

        PageInfo pageInfo = menuService.findMenu(0, 0, null);
        try {
            PrintWriter printWriter = response.getWriter();
            boolean b = false;
            List<Menu> menuList = pageInfo.getList();
            for(Menu menu : menuList) {
                if(!(menu.getMenuname().equals(menuname))) {
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
    //删除指定菜单
    @RequestMapping("/power/menu/deleteMenu")
    public void deleteMenu(int menuid, HttpServletResponse response) {

        int count = menuService.deleteByPrimaryKey(menuid);
        try {
            PrintWriter printWriter = response.getWriter();
            if(count > 0) {
                printWriter.write("<script>alert('菜单删除成功');location.href = " +
                        "'/power/menu/findAllMenu'</script>");
            } else {
                printWriter.write("<script>alert('菜单删除失败');location.href = '/power/menu/findAllMenu'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //批量删除
    @RequestMapping("/power/menu/deleteMenus")
    public void deleteMenus(String[] single, HttpServletResponse response) {

        int count = menuService.deleteMenus(single);
        try {
            PrintWriter printWriter = response.getWriter();
            if (count > 0) {
                printWriter.write("<script>alert('菜单删除成功');location.href = " + "'/power/menu/findAllMenu'</script>");
            } else {
                printWriter.write("<script>alert('菜单删除失败，请检查所选菜单后重试');location.href = '/power/menu/findAllMenu'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
















