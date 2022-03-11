package com.px.questionnaireService.service;

import com.px.commonUtils.R;
import com.px.questionnaireService.entity.Topic;
import com.baomidou.mybatisplus.extension.service.IService;
import com.px.questionnaireService.entity.vo.QuestionReqVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2022-02-22
 */
public interface TopicService extends IService<Topic> {

    R saveQuestion(QuestionReqVo questionReqVo);

    R delQuestion(Integer uid, Map<String, String> params);

    R moveQuestion(Integer uid, Map<String, String> params);
}
