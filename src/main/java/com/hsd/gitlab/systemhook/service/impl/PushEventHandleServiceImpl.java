/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.hsd.gitlab.systemhook.domain.SysProject;
import com.hsd.gitlab.systemhook.domain.SysRPushCommit;
import com.hsd.gitlab.systemhook.service.EventHandleService;

/**
 * Class Description
 * @version Sep 28, 20176:48:58 PM
 * @author Ford.CHEN
 */
@Service("pushEventHandleServiceImpl")
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
    SysCommitServiceImpl sysCommitServiceImpl;
    
    
    /* (non-Javadoc)
     * @see com.hsd.gitlab.systemhook.service.EventHandleService#handle(java.lang.String)
     */
    @Override
    public void handle(String message) {
        PushEvent event = JSON.parseObject(message, PushEvent.class);
        
        //2.1 跟据groupName获取 SysOutgoingGroup 集合outgroupList
        
        //2.2 多线程、异步分发 消息到对应 outgroupList
        //2.2.1 组装消息
        //2.2.2 多线程，分发
        
        
        //持久化 SysAuthor
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
        
        //持久化 SysProject
        EntityWrapper<SysProject> projectWrapper = new EntityWrapper<SysProject>();
        Project projectDto = event.getProject();
        projectWrapper.eq("name",projectDto.getPathWithNamespace());
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
        
        //持久化 SysCommit
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
               
               sysCommitServiceImpl.insert(sysCommit);
               
               sysCommitList.add(sysCommit);
           }
        }
        
        
        //持久化 SysHookPush
        SysHookPush push = new SysHookPush();
        push.setObjectKind(event.getObjectKind());
        push.setEventName(event.getEventName());;
        push.setRef(event.getRef());
        push.setCheckoutSha(event.getCheckoutSha());
        push.setMessage(message);
        push.setBefore(event.getBefore());
        push.setAfter(event.getAfter());
        push.setAuthorId(author.getId());
        push.setProjectId(project.getId());
        push.setTotalCommitsCount(event.getTotalCommitsCount());
       
        
        sysHookPushService.insert(push);
        
        //持久化 R表
        if(! sysCommitList.isEmpty()){
           for(SysCommit commit : sysCommitList){
               SysRPushCommit rPushCommit = new SysRPushCommit();
               rPushCommit.setCommitId(commit.getId());
               rPushCommit.setPushId(push.getId());
               
               sysRPushCommitMapper.insert(rPushCommit);
           }
        }
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
