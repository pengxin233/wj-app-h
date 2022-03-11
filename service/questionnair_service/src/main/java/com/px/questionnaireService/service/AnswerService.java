package com.px.questionnaireService.service;

import com.px.commonUtils.R;
import com.px.questionnaireService.entity.Answer;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2022-02-25
 */
public interface AnswerService extends IService<Answer> {

    R submitAnswer(HttpServletRequest request, Map<String, Object> params);

    /**
     * 得到当前问卷30天的回收情况
     * @param request
     * @param params
     * @return
     */
    R getAnswerInfo(HttpServletRequest request, Map<String, String> params);
}
