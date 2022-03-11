package com.px.questionnaireService.controller;


import cn.hutool.extra.servlet.ServletUtil;
import com.px.commonUtils.R;
import com.px.commonUtils.Util;
import com.px.commonUtils.WjException;
import com.px.questionnaireService.service.QuestionnaireService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

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

    @PostMapping("/newQuestionnaire")
    public R newQuestionnaire(@RequestBody Map<String,String> params,HttpServletRequest request){
        Integer uid = 1;
        Util.getIp(request);
        return questionnaireService.newQuestionnaire(uid,params);
    }


    @GetMapping("/getQuestionnaires")
    public R getQuestionnaires(HttpServletRequest request){
        Integer uid = 1;
        String ip = Util.getIp(request);
        System.out.println(ip);
        return questionnaireService.getQuestionnaires(uid);
    }

    @PostMapping("/delQuestionnaire")
    public R delQuestionnaire(@RequestBody Map<String,String> params){

        Integer uid = 1;
        return questionnaireService.delQuestionnaire(uid,params);
    }

    @GetMapping("/getQuestionnaire")
    public R getQuestionnaire(@RequestParam Integer qid){
        Integer uid = 1;
        return questionnaireService.getQuestionnaire(uid,qid);
    }

    @PostMapping("/changeQuestionnaire")
    public R changeQuestionnaire(@RequestBody Map<String,String> params){
        Integer uid = 1;
        return questionnaireService.changeQuestionnaire(uid,params);
    }

    @PostMapping("/publishQuestionnaire")
    public R publishQuestionnaire(@RequestBody Map<String,String> params){
        Integer uid = 1;
        return questionnaireService.publishQuestionnaire(uid,params);
    }

    @GetMapping("/checkHash")
    public R checkHash(@RequestParam String qid ,@RequestParam String hash){
        Integer uid = 1;
        return questionnaireService.checkHash(uid,qid,hash);
    }


}

