package com.px.questionnaireService.controller;


import com.px.commonUtils.R;
import com.px.questionnaireService.entity.vo.QuestionReqVo;
import com.px.questionnaireService.entity.vo.QuestionVo;
import com.px.questionnaireService.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2022-02-22
 */
@RestController
@CrossOrigin
@RequestMapping("/questionnaireService/topic")
public class TopicController {
    @Autowired
    private TopicService service;

    @PostMapping("/saveQuestion")
    public R saveQuestion(@RequestBody QuestionReqVo questionReqVo){
        Integer uid = 1;
        return service.saveQuestion(questionReqVo);
    }

    @PostMapping("delQuestion")
    public R delQuestion(@RequestBody Map<String,String> params){
        Integer uid = 1;
        return service.delQuestion(uid,params);
    }

    @PostMapping("moveQuestion")
    public R moveQuestion(@RequestBody Map<String,String> params){
        Integer uid = 1;
        return service.moveQuestion(uid,params);
    }
}

