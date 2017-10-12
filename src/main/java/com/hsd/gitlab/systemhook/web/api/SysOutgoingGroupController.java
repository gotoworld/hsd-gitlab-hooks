/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.web.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsd.gitlab.systemhook.domain.SysOutgoingGroup;
import com.hsd.gitlab.systemhook.service.impl.SysOutgoingGroupServiceImpl;
import com.hsd.gitlab.utils.PageInfo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Class Description
 * @version Oct 10, 20171:45:56 PM
 * @author Ford.CHEN
 */
@RestController
@RequestMapping("/api/outgoinggroup")
public class SysOutgoingGroupController {
    
    private final static Logger logger = LoggerFactory.getLogger(SysOutgoingGroupController.class);
    
    @Resource
    SysOutgoingGroupServiceImpl sysOutgoingGroupService;
    
    
    /**
     * 
     * Method Description
     * @version Oct 11, 20173:17:44 PM
     * @author Ford.CHEN
     * @param group
     */
    @ApiOperation(value="Create a Group level of Outgoing", notes="Create a Group level of Outgoing")
    @ApiImplicitParam(name = "sysOutgoingGroup", value = "SysOutgoingGroup to be create", required = true, dataType = "SysOutgoingGroup")
    @PostMapping("/test")
    public void create(@RequestBody SysOutgoingGroup sysOutgoingGroup){
        sysOutgoingGroupService.insert(sysOutgoingGroup);
    }
    
    /**
     * 
     * Method Description
     * @version Oct 11, 20173:05:03 PM
     * @author Ford.CHEN
     * @param pageInfo
     * @return
     */
    @ApiOperation(value="Group level of Outgoing", notes="Group level of Outgoing page list")
    @ApiImplicitParam(name = "pageInfo", value = "分页DTO", required = true, dataType = "PageInfo")
    @PostMapping("/list")
    public PageInfo list(@RequestBody PageInfo pageInfo){
        Page<SysOutgoingGroup> page = new Page<SysOutgoingGroup>(pageInfo.getNowpage(), pageInfo.getPagesize());
        
        EntityWrapper<SysOutgoingGroup> wrapper = new EntityWrapper<SysOutgoingGroup>();
        wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
        
        
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(sysOutgoingGroupService.selectPage(page, wrapper).getRecords());
        
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        
        return pageInfo;
    }
}
