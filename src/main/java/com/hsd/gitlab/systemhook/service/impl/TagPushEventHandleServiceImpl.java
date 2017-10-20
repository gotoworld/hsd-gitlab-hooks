/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.service.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsd.gitlab.systemhook.bean.event.TagPushEvent;
import com.hsd.gitlab.systemhook.domain.SysOutgoingGroup;
import com.hsd.gitlab.systemhook.service.EventHandleService;
import com.hsd.gitlab.type.IMType;
import com.hsd.gitlab.utils.HttpClientUtils;

/**
 * Class Description
 * @version Sep 28, 20176:48:58 PM
 * @author Ford.CHEN
 */
@Service("tagPushEventHandleService")
public class TagPushEventHandleServiceImpl implements EventHandleService {
    
    
    @Resource
    SysOutgoingGroupServiceImpl sysOutgoingGroupService;
    
    /* (non-Javadoc)
     * @see com.hsd.gitlab.systemhook.service.EventHandleService#handle(java.lang.String)
     */
    @Override
    public void handle(String message) {
        //1. parse message to TagPushEvent
        TagPushEvent event = JSON.parseObject(message, TagPushEvent.class);
        
        //2. Outgoing TagPushEvent
        //2.1 跟据groupName获取 SysOutgoingGroup 集合outgroupList
        List<SysOutgoingGroup> outgoingList = sysOutgoingGroupService.selectList(new EntityWrapper<SysOutgoingGroup>());
        
        
        //2.2 多线程、异步分发 消息到对应 outgroupList
        ExecutorService executorService = Executors.newCachedThreadPool();   
        
        if(! outgoingList.isEmpty()){
            for(SysOutgoingGroup outgoingGroup : outgoingList){
                if(outgoingGroup.getGitlabGroupName().equals(event.getProject().getNamespace())){
                    
                    //2.2.1 compose message
                    String textMsg = "";
                    if(IMType.jenkins.equals(outgoingGroup.getImType())){
                        textMsg = message;
                    }else{
                        //TODO
                    }
                    
                    //2.2.2 post message, multi-thread asynchronous
                    executorService.submit(new TaskOfOutgoingPost(textMsg,outgoingGroup.getImUrl()));   
                }
            }
        }
        
        //3. Persist TagPushEvent, for statistic analysis later
//        persistPushEvent(event);
    }
    
    
    /**
     * 
     * Class Description
     * @version Oct 9, 20175:41:59 PM
     * @author Ford.CHEN
     */
    class TaskOfOutgoingPost implements Callable<String> {
        private String textMsg;
        private String outgoingUrl;
        
        public TaskOfOutgoingPost(String textMsg,String outgoingUrl){   
            this.textMsg = textMsg;   
            this.outgoingUrl = outgoingUrl;   
        }
        
        /**
         * 任务的具体过程，一旦任务传给ExecutorService的submit方法， 则该方法自动在一个线程上执行
         */
        @Override
        public String call() throws Exception {
            Long start = System.currentTimeMillis();
            
            //do post
            HttpClientUtils.post(textMsg, outgoingUrl);
            
            return "Task Of Outgoing Post finished, it cost time：" + (System.currentTimeMillis() - start);
        }
    }
    
}
