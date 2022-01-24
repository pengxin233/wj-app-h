package com.px.commonUtils;

public enum PublicEnum {

    WENJUAN(0,"问卷类型"),
    SHIJUAN(1,"试卷类型");

    private String desc;
    private Integer code;

    PublicEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
