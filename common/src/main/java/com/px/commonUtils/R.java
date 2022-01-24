package com.px.commonUtils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private Integer code;
    private String msg;
    private Map<String,Object> data;

    private R(){

    }

    public static R ok(){
        R r = new R();
        r.setCode(200);
        r.setMsg("ok");
        r.setData(new HashMap<>());
        return r;
    }

    public static R error(){
        R r = new R();
        r.setCode(400);
        r.setMsg("error");
        r.setData(new HashMap<>());
        return r;
    }

    public R msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public R data(String k,Object v){
        this.getData().put(k, v);
        return this;
    }

    public R data(Map<String,Object> data){
        this.setData(data);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
