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
 * @since 2022-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Answer对象", description="")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;

    private Integer qid;

    @ApiModelProperty(value = "花费时间  ms")
    private Long costTime;

    @ApiModelProperty(value = "提交问卷时间")
    private Date submitTime;

    @ApiModelProperty(value = "来源")
    private String source;

    @ApiModelProperty(value = "答卷人ip")
    private String ip;


}
