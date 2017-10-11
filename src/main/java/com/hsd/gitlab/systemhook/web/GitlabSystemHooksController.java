/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.web;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hsd.gitlab.systemhook.bean.event.BaseEvent;
import com.hsd.gitlab.systemhook.service.EventHandleService;
import com.hsd.gitlab.systemhook.service.impl.EventHandleServiceFactory;

/**
 * Class Description
 * @version Sep 27, 20171:59:02 PM
 * @author Ford.CHEN
 */
@RestController()
@RequestMapping("/incoming")
public class GitlabSystemHooksController {
    
    private final static Logger logger = LoggerFactory.getLogger(GitlabSystemHooksController.class);
    
    @Resource
    EventHandleServiceFactory eventHandleServiceFactory;
    
    /**
     * 
     * Method Description
     * @version Sep 28, 20176:43:54 PM
     * @author Ford.CHEN
     * @param message
     */
    @PostMapping(value = "/systemhooks", consumes = "application/json")
    public void trigger(@RequestBody String message) {
        logger.info("received hooks message: {}", message);
        
        if(StringUtils.contains(message, "event_name")){
            BaseEvent event = JSON.parseObject(message, BaseEvent.class);
            
            EventHandleService  eventHandleService = eventHandleServiceFactory.getEventHandleService(event.getEventName());
            eventHandleService.handle(message);
        }
        
        logger.info("Finished hooks message handle");
    }
    
    
}
