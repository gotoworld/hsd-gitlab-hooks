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
import com.hsd.gitlab.constant.Constants;
import com.hsd.gitlab.systemhook.domain.SysOutgoingGroup;
import com.hsd.gitlab.systemhook.service.impl.SysOutgoingGroupServiceImpl;
import com.hsd.gitlab.systemhook.vo.BaseVO;
import com.hsd.gitlab.systemhook.vo.ResVo;

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
     * @version Oct 12, 20176:29:59 PM
     * @author Ford.CHEN
     * @param sysOutgoingGroup
     * @return
     */
    @ApiOperation(value="Create a Group level of Outgoing", notes="Create a Group level of Outgoing")
    @ApiImplicitParam(name = "sysOutgoingGroup", value = "SysOutgoingGroup to be create", required = true, dataType = "SysOutgoingGroup")
    @PostMapping("/add")
    public ResVo<BaseVO> addOrEdit(@RequestBody SysOutgoingGroup sysOutgoingGroup){
        logger.info("receied groud create or update:{}",sysOutgoingGroup);
        
        boolean result = sysOutgoingGroupService.insertOrUpdate(sysOutgoingGroup);
        
        return composeResVO(result);
    }

    /**
     * Method Description
     * @version Oct 12, 20176:30:36 PM
     * @author Ford.CHEN
     * @param result
     * @return
     */
    private ResVo<BaseVO> composeResVO(boolean result) {
        ResVo<BaseVO> resVO = new ResVo<BaseVO>();
        if(result){
            resVO.setRetInfo(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG);
        }else{
            resVO.setRetInfo(Constants.FAIL_CODE, Constants.FAIL_CODE);
        }
       
        return resVO;
    }
    
    
    @ApiOperation(value="info Group level of Outgoing", notes="Info Group level of Outgoing")
    @ApiImplicitParam(name = "outgoinggroupId", value = "outgoinggroupId", required = false, dataType = "Long")
    @PostMapping("")
    public SysOutgoingGroup info(@RequestBody Long outgoinggroupId){
        
        SysOutgoingGroup group = sysOutgoingGroupService.selectById(outgoinggroupId);
        
        return group;
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
    @ApiImplicitParam(name = "page", value = "分页DTO", required = true, dataType = "Page<SysOutgoingGroup>")
    @PostMapping("/list")
    public Page<SysOutgoingGroup> list(@RequestBody Page<SysOutgoingGroup> page){
        
        EntityWrapper<SysOutgoingGroup> wrapper = new EntityWrapper<SysOutgoingGroup>();
        wrapper.orderBy(page.getOrderByField(), page.isAsc());
        
        SqlHelper.fillWrapper(page, wrapper);
        page = sysOutgoingGroupService.selectPage(page, wrapper);
        
        return page;
    }
    
    
    @ApiOperation(value="Remove Group level of Outgoing", notes="Remove Group level of Outgoing")
    @ApiImplicitParam(name = "outgoinggroupId", value = "outgoinggroupId", required = true, dataType = "Long")
    @PostMapping("/delete")
    public ResVo<BaseVO> delete(@RequestBody Long outgoinggroupId){
        
        boolean result = sysOutgoingGroupService.deleteById(outgoinggroupId);
        
        return composeResVO(result);
    }
    
}
