package com.px.commonUtils;

import cn.hutool.core.util.StrUtil;

import java.util.Map;

public class ParamsUtil {
    public static void checkState(Map<String,String> params,String... args){
        for (String arg : args) {
            if (StrUtil.isBlank(params.get(arg))){
                throw new WjException(StrUtil.format("[{}] 参数错误",arg));
            }
        }
    }
}
