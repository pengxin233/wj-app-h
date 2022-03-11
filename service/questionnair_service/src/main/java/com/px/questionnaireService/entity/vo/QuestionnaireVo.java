package com.px.questionnaireService.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author pengxin01
 */
@Data
public class QuestionnaireVo {
    private Integer qid;
    private String title;
    private Integer type;
    private Integer uid;
    private Boolean stage;
    private Boolean deadline;
    private String date;
    private Date createTime;
    private Date updateTime;
    private Integer hash;
    private Integer count;
    private Integer aCount;
}
