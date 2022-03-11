package com.px.questionnaireService.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.commonUtils.ParamsUtil;
import com.px.commonUtils.R;
import com.px.commonUtils.Util;
import com.px.questionnaireService.entity.AnsValue;
import com.px.questionnaireService.entity.Answer;
import com.px.questionnaireService.entity.vo.AnswerVo;
import com.px.questionnaireService.mapper.AnsValueMapper;
import com.px.questionnaireService.mapper.AnswerMapper;
import com.px.questionnaireService.service.AnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2022-02-25
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {
    @Autowired
    private AnsValueMapper mapper;

    @Override
    @Transactional
    public R submitAnswer(HttpServletRequest request, Map<String, Object> params) {
        Answer answer = new Answer();
        answer.setSubmitTime(new Date());
        answer.setCostTime(Long.valueOf(String.valueOf(params.get("time"))));
        answer.setIp(Util.getIp(request));
        answer.setQid(Integer.valueOf(String.valueOf(params.get("qid"))));

        baseMapper.insert(answer);

        List<LinkedHashMap<String,Object>> data = (List<LinkedHashMap<String,Object>>) params.get("data");

        for (LinkedHashMap<String, Object> datum : data) {
            AnsValue ansValue = new AnsValue();
            ansValue.setTid((Integer) datum.get("tid"));
            ansValue.setValue(String.valueOf(datum.get("value")));
            ansValue.setAid(answer.getAid());
            mapper.insert(ansValue);
        }

        return R.ok();
    }

    @Override
    public R getAnswerInfo(HttpServletRequest request, Map<String, String> params) {
        ParamsUtil.checkState(params,"qid");
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.eq("qid",params.get("qid"));
        List<Answer> answers = baseMapper.selectList(wrapper);

        List<AnswerVo> list = new ArrayList<>();

        //得到今天的时间
        DateTime date = DateUtil.beginOfDay(new Date());
        date.setMutable(false);

        for (int i=0;i<30;i++){
            AnswerVo answerVo = new AnswerVo();
            DateTime offset = date.offset(DateField.DAY_OF_MONTH, -i);
            answerVo.setDay(offset.toDateStr());
            answerVo.setCount(0);

            list.add(answerVo);
        }

        for (Answer answer : answers) {
            Date submitTime = answer.getSubmitTime();
            DateTime dateTime = DateUtil.beginOfDay(submitTime);

            int between = (int) date.between(dateTime, DateUnit.DAY);

            if (between < 30){
                list.get(between).countAdd();
            }
        }

        return R.ok().data("list",list);
    }
}
