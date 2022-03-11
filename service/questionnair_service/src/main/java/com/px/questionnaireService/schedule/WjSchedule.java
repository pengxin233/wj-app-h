package com.px.questionnaireService.schedule;

import com.px.questionnaireService.service.QuestionnaireService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class WjSchedule {
    @Autowired
    private QuestionnaireService service;
    /**
     * 每天的凌晨开始,刷新已结束的任务
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void executePublishTask(){
        service.executePublishTask();

    }
}
