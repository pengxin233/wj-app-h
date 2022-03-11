package com.px.questionnaireService.controller;


import cn.hutool.core.lang.tree.TreeNode;
import com.px.commonUtils.R;
import com.px.commonUtils.Util;
import com.px.questionnaireService.entity.AnsValue;
import com.px.questionnaireService.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author px
 * @since 2022-02-25
 */
@RestController
@CrossOrigin
@RequestMapping("/questionnaireService/answer")
public class AnswerController {
    @Autowired
    private AnswerService service;

    @PostMapping("submitAnswer")
    public R submitAnswer(HttpServletRequest request, @RequestBody Map<String,Object> params){

        return service.submitAnswer(request,params);
    }

    @PostMapping("getAnswerInfo")
    public R getAnswerInfo(HttpServletRequest request, @RequestBody Map<String,String> params){

        return service.getAnswerInfo(request,params);
    }
}

