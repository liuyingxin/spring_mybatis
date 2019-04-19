package com.xin.Spring.bean;

/**
 * @author ly
 * @date 2019/4/19
 */
public class Dept {
    private String deptid;
    private String deptname;

    public Dept() {
    }

    public Dept(String deptid, String deptname) {
        this.deptid = deptid;
        this.deptname = deptname;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
}
