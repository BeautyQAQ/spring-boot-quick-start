package com.quick.start.securityframework.dto;

import java.io.Serializable;
import java.util.List;

public class MenuIndexDto implements Serializable {

    private Integer id;

    private Integer parentId;

    private String title;

    private String icon;

    private Integer type;

    private String href;

    private String permission;

    private List<MenuIndexDto> children;

    public MenuIndexDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public List<MenuIndexDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuIndexDto> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MenuIndexDto{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", type=" + type +
                ", href='" + href + '\'' +
                ", permission='" + permission + '\'' +
                ", children=" + children +
                '}';
    }
}
