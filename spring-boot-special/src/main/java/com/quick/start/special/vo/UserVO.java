package com.quick.start.special.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户 VO")
public class UserVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Integer id;
    @ApiModelProperty(value = "账号", required = true, example = "123456")
    private String username;

    public Integer getId() {
        return id;
    }

    public UserVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserVO setUsername(String username) {
        this.username = username;
        return this;
    }

}
