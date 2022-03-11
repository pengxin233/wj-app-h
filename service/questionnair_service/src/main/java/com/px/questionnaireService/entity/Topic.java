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
 * @since 2022-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Topic对象", description="")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "题目id")
    @TableId(value = "tid", type = IdType.AUTO)
    private Integer tid;

    @ApiModelProperty(value = "题目")
    private String title;

    @ApiModelProperty(value = "options选项")
    private String options;

    @ApiModelProperty(value = "题目类型")
    private String type;

    @ApiModelProperty(value = "是否必须 (0 false，  1  true)")
    private Boolean required;

    @ApiModelProperty(value = "题目顺序")
    private Integer sort;

    @ApiModelProperty(value = "问卷id")
    private Integer qid;

    @ApiModelProperty(value = "答案id")
    private Integer rid;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

}
