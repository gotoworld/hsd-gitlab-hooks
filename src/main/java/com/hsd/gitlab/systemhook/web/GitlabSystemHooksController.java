/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.web;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/systemhooks", method = RequestMethod.POST, consumes = "application/json")
    public void trigger(@RequestBody String message) {
        logger.info("received hooks message: {}", message);
        
        if(StringUtils.contains(message, "event_name")){
            BaseEvent event = JSON.parseObject(message, BaseEvent.class);
            
            EventHandleService  eventHandleService = eventHandleServiceFactory.getEventHandleService(event.getEventName());
            eventHandleService.handle(message);
        }
        
        logger.info("Finished hooks message handle");
    }
    
    public static void main(String[] args) {
        String message  = "{\"object_kind\":\"push\",\"event_name\":\"push\",\"before\":\"beb29d6aae20e17f44f68235c1d9534ccd871277\",\"after\":\"a54866513772560b9120b268c00f6cafe565bab8\",\"ref\":\"refs/heads/abranch\",\"checkout_sha\":\"a54866513772560b9120b268c00f6cafe565bab8\",\"message\":null,\"user_id\":1,\"user_name\":\"Administrator\",\"user_username\":\"root\",\"user_email\":\"admin@example.com\",\"user_avatar\":\"http://www.gravatar.com/avatar/e64c7d89f26bd1972efa854d13d7dd61?s=80\u0026d=identicon\",\"project_id\":1,\"project\":{\"name\":\"ss\",\"description\":\"\",\"web_url\":\"http://192.168.254.205/infrastructure/ss\",\"avatar_url\":null,\"git_ssh_url\":\"git@192.168.254.205:infrastructure/ss.git\",\"git_http_url\":\"http://192.168.254.205/infrastructure/ss.git\",\"namespace\":\"infrastructure\",\"visibility_level\":0,\"path_with_namespace\":\"infrastructure/ss\",\"default_branch\":\"master\",\"ci_config_path\":null,\"homepage\":\"http://192.168.254.205/infrastructure/ss\",\"url\":\"git@192.168.254.205:infrastructure/ss.git\",\"ssh_url\":\"git@192.168.254.205:infrastructure/ss.git\",\"http_url\":\"http://192.168.254.205/infrastructure/ss.git\"},\"commits\":[{\"id\":\"a54866513772560b9120b268c00f6cafe565bab8\",\"message\":\"add\n\",\"timestamp\":\"2017-09-29T12:45:53+08:00\",\"url\":\"http://192.168.254.205/infrastructure/ss/commit/a54866513772560b9120b268c00f6cafe565bab8\",\"author\":{\"name\":\"chenfangri\",\"email\":\"chenfangri@heshidai.com\"},\"added\":[\"aaa11.tx\"],\"modified\":[],\"removed\":[]}],\"total_commits_count\":1,\"repository\":{\"name\":\"ss\",\"url\":\"git@192.168.254.205:infrastructure/ss.git\",\"description\":\"\",\"homepage\":\"http://192.168.254.205/infrastructure/ss\",\"git_http_url\":\"http://192.168.254.205/infrastructure/ss.git\",\"git_ssh_url\":\"git@192.168.254.205:infrastructure/ss.git\",\"visibility_level\":0}}";
   
        BaseEvent event = JSON.parseObject(message, BaseEvent.class);
        
        System.out.println(event);
        
        
    }
    
    

    /**
     * @param eventHandleServiceFactory the eventHandleServiceFactory to set
     */
    public void setEventHandleServiceFactory(EventHandleServiceFactory eventHandleServiceFactory) {
        this.eventHandleServiceFactory = eventHandleServiceFactory;
    }
    
}
