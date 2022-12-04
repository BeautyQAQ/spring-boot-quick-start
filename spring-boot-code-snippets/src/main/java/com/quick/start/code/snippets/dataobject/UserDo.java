package com.quick.start.code.snippets.dataobject;

public class UserDo {

    /**
     * 用户编号
     */
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码（明文）
     * ps：生产环境下，不要明文
     */
    private String password;

    public Integer getId() {
        return id;
    }

    public UserDo setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDo setPassword(String password) {
        this.password = password;
        return this;
    }
}
