package com.px.questionnaireService.entity.vo;

import lombok.Data;

@Data
public class AnswerVo {
    private String day;
    private Integer count;

    public void countAdd(){
        this.count++;
    }
}
