package com.px.questionnaireService.controller;


import com.px.commonUtils.PublicEnum;
import com.px.commonUtils.R;
import com.px.questionnaireService.entity.vo.QuestionnaireVo;
import com.px.questionnaireService.service.QuestionnaireService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2022-01-20
 */
@Log4j2
@RestController
@RequestMapping("/questionnaireService/questionnaire")
@CrossOrigin
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping("/newQuestionnaire")
    public R newQuestionnaire(@RequestParam Integer qType, HttpServletRequest request){
        Integer uid = 1;
        return questionnaireService.newQuestionnaire(uid,qType);
    }


    @GetMapping("/getQuestionnaires")
    public R getQuestionnaires(){
        Integer uid = 1;
        return questionnaireService.getQuestionnaires(uid);
    }

    @GetMapping("/delQuestionnaire")
    public R delQuestionnaire(@RequestParam Integer qid){
        Integer uid = 1;
        return questionnaireService.delQuestionnaire(uid,qid);
    }
}

