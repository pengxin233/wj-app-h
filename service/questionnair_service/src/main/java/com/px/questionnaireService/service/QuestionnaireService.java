package com.px.questionnaireService.service;

import com.px.commonUtils.R;
import com.px.questionnaireService.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2022-01-20
 */
public interface QuestionnaireService extends IService<Questionnaire> {

    R getQuestionnaires(Integer uid);

    R delQuestionnaire(Integer uid, Map<String, String> params);

    R getQuestionnaire(Integer uid, Integer qid);

    R newQuestionnaire(Integer uid, Map<String, String> params);

    R changeQuestionnaire(Integer uid, Map<String, String> params);

    R publishQuestionnaire(Integer uid, Map<String, String> params);

    void executePublishTask();

    R checkHash(Integer uid, String qid , String hash);
}
