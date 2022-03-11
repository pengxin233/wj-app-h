package com.px.questionnaireService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.commonUtils.ParamsUtil;
import com.px.commonUtils.R;
import com.px.commonUtils.WjException;
import com.px.questionnaireService.entity.Topic;
import com.px.questionnaireService.entity.vo.QuestionReqVo;
import com.px.questionnaireService.mapper.TopicMapper;
import com.px.questionnaireService.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2022-02-22
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Override
    public R saveQuestion(QuestionReqVo questionReqVo) {
        String changedWay = questionReqVo.getChangedWay();

        Topic topic;
        if ("new".equals(changedWay)){
            topic = new Topic();
            topic.setOptions(questionReqVo.getOptions());
            topic.setQid(questionReqVo.getQid());
            topic.setRequired(questionReqVo.getRequired());
            topic.setSort(questionReqVo.getSort());
            topic.setType(questionReqVo.getType());
            topic.setTitle(questionReqVo.getTitle());
            baseMapper.insert(topic);
            return R.ok().data("msg","new").data("tid",topic.getTid());
        }

        System.out.println("====================================");

        topic = baseMapper.selectById(questionReqVo.getTid());

        for (String s : changedWay.split("&&&")) {
            if ("title".equals(s)){
                topic.setTitle(questionReqVo.getTitle());
            }else if ("options".equals(s)){
                topic.setOptions(questionReqVo.getOptions());
            }else if ("required".equals(s)){
                topic.setRequired(questionReqVo.getRequired());
            }
        }
        baseMapper.updateById(topic);

        return R.ok().data("msg","update");
    }

    @Override
    public R delQuestion(Integer uid, Map<String, String> params) {
        ParamsUtil.checkState(params,"tid");
        String tid = params.get("tid");

        int i = baseMapper.deleteById(Integer.valueOf(tid));

        return i>0 ? R.ok().data("msg","删除成功"):R.error().msg("删除失败");

    }

    @Override
    @Transactional
    public R moveQuestion(Integer uid, Map<String, String> params) {
        ParamsUtil.checkState(params,"now","target");

        Integer now = Integer.valueOf(params.get("now"));
        Integer target = Integer.valueOf(params.get("target"));

        Topic topicNow = baseMapper.selectById(now);
        Topic topicTarget = baseMapper.selectById(target);

        Integer sort = topicNow.getSort();
        topicNow.setSort(topicTarget.getSort());
        topicTarget.setSort(sort);

        baseMapper.updateById(topicNow);
        baseMapper.updateById(topicTarget);

        return R.ok();
    }
}
