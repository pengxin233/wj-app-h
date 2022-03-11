package com.px.questionnaireService.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author px
 * @since 2022-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Questionnaire对象", description="")
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "问卷id")
    @TableId(value = "qid", type = IdType.AUTO)
    private Integer qid;

    @ApiModelProperty(value = "问卷名")
    private String title;

    @ApiModelProperty(value = "问卷类型")
    private Integer type;

    @ApiModelProperty(value = "问卷创建者")
    private Integer uid;

    @ApiModelProperty(value = "是否发布 0：未发布  1：已发布")
    private Boolean stage;

    @ApiModelProperty(value = "是否设置截止时间 0：未设置  1：设置")
    private Boolean deadline;

    @ApiModelProperty(value = "截止日期")
    private String date;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


}
