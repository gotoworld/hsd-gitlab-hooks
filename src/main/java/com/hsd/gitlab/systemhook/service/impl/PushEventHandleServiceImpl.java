/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hsd.gitlab.systemhook.bean.Commits;
import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.event.PushEvent;
import com.hsd.gitlab.systemhook.dao.SysRPushCommitMapper;
import com.hsd.gitlab.systemhook.domain.SysAuthor;
import com.hsd.gitlab.systemhook.domain.SysCommit;
import com.hsd.gitlab.systemhook.domain.SysHookPush;
import com.hsd.gitlab.systemhook.domain.SysOutgoingGroup;
import com.hsd.gitlab.systemhook.domain.SysProject;
import com.hsd.gitlab.systemhook.domain.SysRPushCommit;
import com.hsd.gitlab.systemhook.service.EventHandleService;
import com.hsd.gitlab.type.IMType;
import com.hsd.gitlab.utils.HttpClientUtils;

/**
 * Class Description
 * @version Sep 28, 20176:48:58 PM
 * @author Ford.CHEN
 */
@Service("pushEventHandleService")
public class PushEventHandleServiceImpl implements EventHandleService {
    
    private final static Logger logger = LoggerFactory.getLogger(PushEventHandleServiceImpl.class);
    
    @Resource
    SysAuthorServiceImpl sysAuthorService;
    
    @Resource
    SysProjectServiceImp sysProjectService;
    
    @Resource
    SysHookPushServiceImp sysHookPushService;
    
    @Resource
    SysRPushCommitMapper sysRPushCommitMapper;
    
    @Resource
    SysCommitServiceImpl sysCommitService;
    
    @Resource
    SysOutgoingGroupServiceImpl sysOutgoingGroupService;
    
    
    /* (non-Javadoc)
     * @see com.hsd.gitlab.systemhook.service.EventHandleService#handle(java.lang.String)
     */
    @Override
    public void handle(String message) {
        //1. parse message to PushEvent
        PushEvent event = JSON.parseObject(message, PushEvent.class);
        
        //2. Outgoing PushEvent
        outgoingPushEvent(event);
        
        //3. Persist PushEvent, for statistic analysis later
        persistPushEvent(event);
    }


    /**
     * Method Description
     * @version Oct 9, 20174:33:55 PM
     * @author Ford.CHEN
     * @param event
     * @throws IOException
     * @throws ClientProtocolException
     */
    private void outgoingPushEvent(PushEvent event)  {
        //2.1 跟据groupName获取 SysOutgoingGroup 集合outgroupList
        List<SysOutgoingGroup> outgoingList = sysOutgoingGroupService.selectList(new EntityWrapper<SysOutgoingGroup>());
        
        
        //2.2 多线程、异步分发 消息到对应 outgroupList
        ExecutorService executorService = Executors.newCachedThreadPool();   
        
        if(! outgoingList.isEmpty()){
            for(SysOutgoingGroup outgoingGroup : outgoingList){
                if(outgoingGroup.getGitlabGroupName().equals(event.getProject().getNamespace())){
                    
                    //2.2.1 compose message
                    String textMsg = "";
                    if(IMType.dingtalk.equals(outgoingGroup.getImType())){
                        event.toDingTalkMarkdown();
                    }else if(IMType.slack.equals(outgoingGroup.getImType())){
                        //TODO FOR Slack
                    }else{
                        //TODO
                    }
                    
                    //2.2.2 post message, multi-thread asynchronous
                    executorService.submit(new TaskOfOutgoingPost(textMsg,outgoingGroup.getIm_url()));   
                }
            }
        }
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
    
    
 


    /**
     * Method Description
     * @version Oct 9, 20174:32:56 PM
     * @author Ford.CHEN
     * @param event
     */
    
    private void persistPushEvent(PushEvent event) {
        //3.1 持久化 SysAuthor
        EntityWrapper<SysAuthor> wrapper = new EntityWrapper<SysAuthor>();
        wrapper.eq("email", event.getUserEmail());
        SysAuthor author = sysAuthorService.selectOne(wrapper);
        
        if(author == null){//TODO redis缓存
            author = new SysAuthor();
            author.setGitlabUserId(event.getUserId());
            author.setUserName(event.getUserUsername());//English name
            author.setName(event.getUsername());
            author.setEmail(event.getUserEmail());
            author.setUserAvatar(event.getUserAvatar());
            
            sysAuthorService.insert(author);
        }
        
        //3.2 持久化 SysProject
        EntityWrapper<SysProject> projectWrapper = new EntityWrapper<SysProject>();
        Project projectDto = event.getProject();
        projectWrapper.eq("name",projectDto.getName());
        SysProject project = sysProjectService.selectOne(projectWrapper);
        
        
        if(project == null){//TODO redis缓存
            project = new SysProject();
            project.setName(projectDto.getName());
            project.setWebUrl(projectDto.getWebUrl());
            project.setAvatarUrl(projectDto.getAvatarUrl());
            project.setGitSshUrl(projectDto.getGitSshUrl());
            project.setGitHttpUrl(projectDto.getGitHttpUrl());
            project.setGroupName(projectDto.getNamespace());
            project.setPathWithNamespace(projectDto.getPathWithNamespace());
            
            sysProjectService.insert(project);
        }
        
        //3.3 持久化 SysCommit
        List<SysCommit> sysCommitList = new ArrayList<SysCommit>();
        
        List<Commits> commits = event.getCommits();
        if(! commits.isEmpty()){
           for(Commits commit : commits){
               SysCommit sysCommit = new SysCommit();
               
               sysCommit.setCheckoutSha(commit.getId());
               sysCommit.setMessage(commit.getMessage());
               sysCommit.setCommitTime(commit.getTimestamp());
               sysCommit.setGitlabUrl(commit.getUrl());
               
               EntityWrapper<SysAuthor> cAuthoWrapper = new EntityWrapper<SysAuthor>();
               cAuthoWrapper.eq("email", event.getUserEmail());
               SysAuthor cSysAuthor = sysAuthorService.selectOne(cAuthoWrapper);
               if(cSysAuthor == null){
                   cSysAuthor = new SysAuthor();
                   cSysAuthor.setGitlabUserId(event.getUserId());
                   cSysAuthor.setUserName(event.getUserUsername());//English name
                   cSysAuthor.setName(event.getUsername());
                   cSysAuthor.setEmail(event.getUserEmail());
                   cSysAuthor.setUserAvatar(event.getUserAvatar());
                   
                   sysAuthorService.insert(cSysAuthor);
               }
               
               sysCommit.setAuthorId(cSysAuthor.getId());
               
               sysCommit.setProjectId(project.getId());
               
               sysCommitService.insert(sysCommit);
               
               sysCommitList.add(sysCommit);
           }
        }
        
        
        //3.4 持久化 SysHookPush
        SysHookPush push = new SysHookPush();
        push.setObjectKind(event.getObjectKind());
        push.setEventName(event.getEventName());;
        push.setRef(event.getRef());
        push.setCheckoutSha(event.getCheckoutSha());
        push.setMessage(event.getMessage());
        push.setBefore(event.getBefore());
        push.setAfter(event.getAfter());
        push.setAuthorId(author.getId());
        push.setProjectId(project.getId());
        push.setTotalCommitsCount(event.getTotalCommitsCount());
       
        
        sysHookPushService.insert(push);
        
        //3.5 持久化 R表, i miss for Hibernate
        if(! sysCommitList.isEmpty()){
           for(SysCommit commit : sysCommitList){
               SysRPushCommit rPushCommit = new SysRPushCommit();
               rPushCommit.setCommitId(commit.getId());
               rPushCommit.setPushId(push.getId());
               
               sysRPushCommitMapper.insert(rPushCommit);
           }
        }
    }
    

    /**
     * @param sysOutgoingGroupService the sysOutgoingGroupService to set
     */
    public void setSysOutgoingGroupService(SysOutgoingGroupServiceImpl sysOutgoingGroupService) {
        this.sysOutgoingGroupService = sysOutgoingGroupService;
    }

    /**
     * @param sysAuthorService the sysAuthorService to set
     */
    public void setSysAuthorService(SysAuthorServiceImpl sysAuthorService) {
        this.sysAuthorService = sysAuthorService;
    }


    /**
     * @param sysProjectService the sysProjectService to set
     */
    public void setSysProjectService(SysProjectServiceImp sysProjectService) {
        this.sysProjectService = sysProjectService;
    }


    /**
     * @param sysHookPushService the sysHookPushService to set
     */
    public void setSysHookPushService(SysHookPushServiceImp sysHookPushService) {
        this.sysHookPushService = sysHookPushService;
    }


    /**
     * @param sysRPushCommitMapper the sysRPushCommitMapper to set
     */
    public void setSysRPushCommitMapper(SysRPushCommitMapper sysRPushCommitMapper) {
        this.sysRPushCommitMapper = sysRPushCommitMapper;
    }


    /**
     * @param sysCommitServiceImpl the sysCommitServiceImpl to set
     */
    public void setSysCommitServiceImpl(SysCommitServiceImpl sysCommitServiceImpl) {
        this.sysCommitService = sysCommitServiceImpl;
    }
    
}
