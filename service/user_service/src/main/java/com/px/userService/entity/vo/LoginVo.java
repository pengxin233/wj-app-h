package com.px.userService.entity.vo;

import lombok.Data;

@Data
public class LoginVo {
    private String user;
    private String code;
    private Integer loginType;

    @Override
    public String toString() {
        return "LoginVo{" +
                "user='" + user + '\'' +
                ", code='" + code + '\'' +
                ", loginType=" + loginType +
                '}';
    }
}
