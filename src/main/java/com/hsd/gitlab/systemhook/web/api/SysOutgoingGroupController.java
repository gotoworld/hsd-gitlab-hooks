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
    @PostMapping("")
    public ResVo<BaseVO> create(@RequestBody SysOutgoingGroup sysOutgoingGroup){
        logger.info("receied groud create:{}",sysOutgoingGroup);
        
        boolean result = sysOutgoingGroupService.insert(sysOutgoingGroup);
        
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
    public Page<SysOutgoingGroup> list(@RequestBody Page<SysOutgoingGroup> page){
        
        EntityWrapper<SysOutgoingGroup> wrapper = new EntityWrapper<SysOutgoingGroup>();
        wrapper.orderBy(page.getOrderByField(), page.isAsc());
        
        SqlHelper.fillWrapper(page, wrapper);
        page = sysOutgoingGroupService.selectPage(page, wrapper);
        
        return page;
    }
    
    
/*    *//**
     * 
     * Method Description
     * @version Oct 11, 20173:05:03 PM
     * @author Ford.CHEN
     * @param pageInfo
     * @return
     *//*
    @ApiOperation(value="Group level of Outgoing", notes="Group level of Outgoing page list")
    @ApiImplicitParam(name = "pageInfo", value = "分页DTO", required = true, dataType = "PageInfo")
    @PostMapping("/list")
    public PageInfo list(@RequestBody Page<Object> page){
        Page<SysOutgoingGroup> page = new Page<SysOutgoingGroup>(pageInfo.getNowpage(), pageInfo.getPagesize());
        
        EntityWrapper<SysOutgoingGroup> wrapper = new EntityWrapper<SysOutgoingGroup>();
        wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
        
        
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(sysOutgoingGroupService.selectPage(page, wrapper).getRecords());
        
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        
        return pageInfo;
    }
*/
}
