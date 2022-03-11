package com.px.questionnaireService.entity.vo;

import lombok.Data;

@Data
public class QuestionReqVo {
    private Integer qid;
    private Integer tid;
    private String title;
    private String options;
    private Boolean required;
    private String type;
    private Integer sort;
    private String changedWay;
}
