package com.bean;

import java.util.List;

public class Menu {
    private Integer menuid;

    private String menuname;

    private Integer upmenuid;

    private String menupath;

    private Integer menustate;

    private String menuremark;
    //一级目录保存二级目录
    private List<Menu> secondMenuList;
    //中间表
    private Middle middle;

    public Middle getMiddle() {
        return middle;
    }

    public void setMiddle(Middle middle) {
        this.middle = middle;
    }

    public List<Menu> getSecondMenuList() {
        return secondMenuList;
    }

    public void setSecondMenuList(List<Menu> secondMenuList) {
        this.secondMenuList = secondMenuList;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuid=" + menuid + ", menuname='" + menuname + '\'' + ", upmenuid=" + upmenuid + ", menupath='" + menupath + '\'' + ", menustate=" + menustate + ", menuremark='" + menuremark + '\'' + ", secondMenuList=" + secondMenuList + '}';
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public Integer getUpmenuid() {
        return upmenuid;
    }

    public void setUpmenuid(Integer upmenuid) {
        this.upmenuid = upmenuid;
    }

    public String getMenupath() {
        return menupath;
    }

    public void setMenupath(String menupath) {
        this.menupath = menupath == null ? null : menupath.trim();
    }

    public Integer getMenustate() {
        return menustate;
    }

    public void setMenustate(Integer menustate) {
        this.menustate = menustate;
    }

    public String getMenuremark() {
        return menuremark;
    }

    public void setMenuremark(String menuremark) {
        this.menuremark = menuremark == null ? null : menuremark.trim();
    }
}