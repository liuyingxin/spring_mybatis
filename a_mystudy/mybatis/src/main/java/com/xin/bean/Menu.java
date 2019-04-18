package com.xin.bean;

import java.util.List;

/**
 * @author ly
 * @date 2019/4/18
 */
public class Menu {
    private Integer id;
    private Integer pid;
    private  String menuname;
    private List<Menu> childmenus;

    public List<Menu> getChildmenus() {
        return childmenus;
    }

    public void setChildmenus(List<Menu> childmenus) {
        this.childmenus = childmenus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }
}
