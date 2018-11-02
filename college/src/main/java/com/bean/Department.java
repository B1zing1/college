package com.bean;

public class Department {
    private Integer departid;

    private String departname;

    @Override
    public String toString() {
        return "Department{" + "departid=" + departid + ", departname='" + departname + '\'' + '}';
    }

    public Department() {
    }

    public Department(Integer departid, String departname) {
        this.departid = departid;
        this.departname = departname;
    }

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname == null ? null : departname.trim();
    }
}