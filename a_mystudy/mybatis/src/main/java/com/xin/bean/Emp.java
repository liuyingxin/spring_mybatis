package com.xin.bean;

import java.io.Serializable;

/**
 * @author ly
 * @date 2019/4/17
 */
public class Emp implements Serializable {
    private int id;
    private String eno;
    private String ename;

    public String getEno() {
        return eno;
    }

    public Emp() {
    }

    public Emp(int id, String eno, String ename) {
        this.id = id;
        this.eno = eno;
        this.ename = ename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEno(String eno) {
        this.eno = eno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
