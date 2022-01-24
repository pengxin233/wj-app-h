package com.px.commonUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author pengxin
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WjException extends RuntimeException{
    private Integer code;

    private String msg;

    public WjException(String msg){
        this.code = 400;
        this.msg = msg;
    }
}
