package com.px.commonUtils;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2
public class GlobalException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        log.error(e.toString());
        e.printStackTrace();
        return R.error().msg("出现了未知的错误");
    }


    @ExceptionHandler(WjException.class)
    @ResponseBody
    public R wjError(WjException e){
        log.error(e.getMsg());
        return R.error().msg(e.getMsg()).code(e.getCode());
    }
}
