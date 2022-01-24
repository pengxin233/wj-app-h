package com.px.questionnaireService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.commonUtils.R;
import com.px.commonUtils.WjException;
import com.px.questionnaireService.entity.Questionnaire;
import com.px.questionnaireService.mapper.QuestionnaireMapper;
import com.px.questionnaireService.service.QuestionnaireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2022-01-20
 */
@Log4j2
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {

    @Override
    public R newQuestionnaire(Integer uid, Integer qTYpe) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setUid(uid);
        questionnaire.setQType(qTYpe);
        questionnaire.setQName((qTYpe == 0) ? "新建问卷":"新建试卷");
        baseMapper.insert(questionnaire);

        return R.ok().data("qid",questionnaire.getQid());
    }

    @Override
    public R getQuestionnaires(Integer uid) {
        QueryWrapper<Questionnaire> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);

        List<Questionnaire> questionnaires = baseMapper.selectList(wrapper);
        return R.ok().data("list",questionnaires);
    }

    @Override
    public R delQuestionnaire(Integer uid, Integer qid) {
        Questionnaire questionnaire = baseMapper.selectById(qid);

        if (!uid.equals(questionnaire.getUid())){
            throw new WjException("没有权限删除该问卷");
        }

        int i = baseMapper.deleteById(qid);

        if (i<=0){
            throw new WjException("删除失败");
        }
        return R.ok();
    }
}
