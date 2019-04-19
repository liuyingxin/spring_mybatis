package com.xin.Spring.bean;

/**
 * @author ly
 * @date 2019/4/19
 */
public class User {
    private String usercode;
    private String username;
    private String deptname;

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
//    private Dept dept;
//
//    public Dept getDept() {
//        return dept;
//    }
//
//    public void setDept(Dept dept) {
//        this.dept = dept;
//    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
