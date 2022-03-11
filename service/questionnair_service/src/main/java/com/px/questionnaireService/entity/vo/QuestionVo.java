package com.px.questionnaireService.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author pengxin01
 */
@Data
public class QuestionVo {
    private Integer tid;
    private Integer sort;
    private String title;
    private String type;
    private Boolean required;
    private String options;
}
