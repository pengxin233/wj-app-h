package com.px.questionnaireService.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.commonUtils.ParamsUtil;
import com.px.commonUtils.R;
import com.px.commonUtils.WjException;
import com.px.questionnaireService.entity.Answer;
import com.px.questionnaireService.entity.Questionnaire;
import com.px.questionnaireService.entity.Topic;
import com.px.questionnaireService.entity.vo.QuestionVo;
import com.px.questionnaireService.entity.vo.QuestionnaireVo;
import com.px.questionnaireService.mapper.AnswerMapper;
import com.px.questionnaireService.mapper.QuestionnaireMapper;
import com.px.questionnaireService.mapper.TopicMapper;
import com.px.questionnaireService.service.QuestionnaireService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public R newQuestionnaire(Integer uid, Map<String, String> params) {
        ParamsUtil.checkState(params,"type","title");

        String type = params.get("type");
        String title = params.get("title");

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setUid(uid);
        questionnaire.setType(Integer.valueOf(type));
        questionnaire.setTitle(title);
        questionnaire.setStage(false);
        questionnaire.setDeadline(false);
        log.info(questionnaire);
        baseMapper.insert(questionnaire);

        return R.ok().data("qid",questionnaire.getQid());
    }

    @Override
    public R changeQuestionnaire(Integer uid, Map<String, String> params) {
        ParamsUtil.checkState(params,"qid");
        log.info(params);

        Questionnaire questionnaire = baseMapper.selectById(Integer.valueOf(params.get("qid")));

        String title = params.get("title");
        String stage = params.get("stage");
        String deadline = params.get("deadline");
        String date = params.get("date");

        if (!StrUtil.isBlank(title)){
            if (title.equals(questionnaire.getTitle())){
                throw new WjException("标题未发生变化！");
            }
            questionnaire.setTitle(title);
        }

        if (!StrUtil.isBlank(stage)){
            boolean changedStage = Boolean.parseBoolean(stage);

            if (changedStage == questionnaire.getStage()){
                throw new WjException("状态改变出现异常，请刷新页面！");
            }
            questionnaire.setStage(changedStage);
        }

        if (!StrUtil.isBlank(deadline)){
            boolean changedDeadline = Boolean.parseBoolean(deadline);
            if (changedDeadline == questionnaire.getDeadline()){
                throw new WjException("状态改变出现异常，请刷新页面！");
            }
            questionnaire.setDeadline(changedDeadline);
        }

        if (!StrUtil.isBlank(date)){
            questionnaire.setDate(date);
        }

        int i = baseMapper.updateById(questionnaire);

        return i>0 ? R.ok():R.error();
    }

    @Override
    public R publishQuestionnaire(Integer uid, Map<String, String> params) {
        ParamsUtil.checkState(params,"qid");

        Questionnaire questionnaire = baseMapper.selectById(Integer.valueOf(params.get("qid")));

        questionnaire.setStage(true);

        int i = baseMapper.updateById(questionnaire);

        return i>0 ? R.ok():R.error();
    }

    @Override
    public void executePublishTask() {
        QueryWrapper<Questionnaire> wrapper = new QueryWrapper<>();
        wrapper.eq("stage",true);
        List<Questionnaire> questionnaires = baseMapper.selectList(wrapper);

        String today = DateUtil.today();

        try {
            questionnaires.parallelStream().forEach(questionnaire -> {
                if (CompareUtil.compare(questionnaire.getDate(),today,false) < 0){
                    questionnaire.setStage(false);
                    baseMapper.updateById(questionnaire);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public R checkHash(Integer uid, String qid, String hash) {
        int i = HashUtil.pjwHash(uid +"\001"+ qid);

        if (hash.equals(String.valueOf(i))){
            return R.ok();
        }
        return R.error().msg("hash校验失败");
    }


    @Override
    public R getQuestionnaires(Integer uid) {
        QueryWrapper<Questionnaire> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);

        List<Questionnaire> questionnaires = baseMapper.selectList(wrapper);

        List<QuestionnaireVo> list = questionnaires.stream().map(x -> {
                    Integer qid = x.getQid();
                    //通过qid和uid得到hash
                    int hash = HashUtil.pjwHash(uid + "\001" + qid);

                    //得到问卷的题数
                    QueryWrapper<Topic> tWrapper = new QueryWrapper<>();
                    tWrapper.eq("qid", qid);
                    Integer count = topicMapper.selectCount(tWrapper);

                    //得到回收总数
                    QueryWrapper<Answer> aWrapper = new QueryWrapper<>();
                    aWrapper.eq("qid", qid);
                    Integer aCount = answerMapper.selectCount(aWrapper);

                    QuestionnaireVo questionnaireVo = new QuestionnaireVo();

                    BeanUtil.copyProperties(x,questionnaireVo);
                    questionnaireVo.setCount(count);
                    questionnaireVo.setACount(aCount);
                    questionnaireVo.setHash(hash);

                    return questionnaireVo;
                })
                .collect(Collectors.toList());
        return R.ok().data("list",list);
    }

    @Override
    public R delQuestionnaire(Integer uid, Map<String, String> params) {
        ParamsUtil.checkState(params,"qid");

        Integer qid = Integer.valueOf(params.get("qid"));

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

    @Override
    public R getQuestionnaire(Integer uid, Integer qid) {
        Questionnaire questionnaire = baseMapper.selectById(qid);

        if (questionnaire == null){
            return R.error().data("msg","该问卷不存在！");
        }

        if (!uid.equals(questionnaire.getUid())){
            return R.error().data("msg","没有权限编辑此问卷");
        }

        String date = questionnaire.getDate();
        if (StrUtil.isBlank(date)){
            questionnaire.setDate(DateUtil.today());
        }

        //得到对应的题目
        QueryWrapper<Topic> wrapper = new QueryWrapper<>();
        wrapper.eq("qid",qid);
        List<Topic> topics = topicMapper.selectList(wrapper);

        List<QuestionVo> questionVos = topics.stream().map((question -> {

            QuestionVo questionVo = new QuestionVo();

            questionVo.setOptions(question.getOptions());
            questionVo.setTid(question.getTid());
            questionVo.setSort(question.getSort());
            questionVo.setTitle(question.getTitle());
            questionVo.setType(question.getType());
            questionVo.setRequired(question.getRequired());
            return questionVo;
        })).sorted(Comparator.comparingInt(QuestionVo::getSort)).collect(Collectors.toList());

        return R.ok().data("questionnaire",questionnaire).data("questions",questionVos);
    }
}
