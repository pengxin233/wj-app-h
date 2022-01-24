package com.px.questionnaireService.service;

import com.px.commonUtils.R;
import com.px.questionnaireService.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2022-01-20
 */
public interface QuestionnaireService extends IService<Questionnaire> {

    R newQuestionnaire(Integer uid, Integer qTYpe);

    R getQuestionnaires(Integer uid);

    R delQuestionnaire(Integer uid, Integer qid);
}
