package com.hsd.gitlab.systemhook.dao;

import javax.annotation.Resource;

import org.junit.Test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hsd.gitlab.BaseJunit;
import com.hsd.gitlab.systemhook.domain.SysOutgoingGroup;
import com.hsd.gitlab.type.IMType;
import com.hsd.gitlab.utils.PageInfo;

import junit.framework.Assert;

/**
 * 
 * Class Description
 * @version Jun 26, 20174:01:49 PM
 * @author Ford.CHEN
 */
public class SysOutgoingGroupMapperTest extends BaseJunit {
    
    @Resource
    private SysOutgoingGroupMapper mapper;
    
    @Test
    public void testSave(){
        SysOutgoingGroup outgoing = new SysOutgoingGroup(); 
        outgoing.setGitlabGroupName("root-test");
        outgoing.setIm_url("http://sdfsdfs.com");
        outgoing.setImType(IMType.dingtalk);
        
        mapper.insert(outgoing);
    }
    
    @Test
    public void testPageQuery(){
        PageInfo pageInfo = new PageInfo(1,20);
        
        
        Page<SysOutgoingGroup> page = new Page<SysOutgoingGroup>(pageInfo.getNowpage(), pageInfo.getSize());
        EntityWrapper<SysOutgoingGroup> wrapper = new EntityWrapper<SysOutgoingGroup>();
        wrapper.orderBy(pageInfo.getSort(), pageInfo.getOrder().equalsIgnoreCase("ASC"));
        SqlHelper.fillWrapper(page, wrapper);
        page.setRecords(mapper.selectPage(page, wrapper));
        pageInfo.setRows(page.getRecords());
        pageInfo.setTotal(page.getTotal());
        
        Assert.assertTrue("", pageInfo.getTotal()>0);
    }
    
    @Test
    public void testDelete(){
        EntityWrapper<SysOutgoingGroup> wrapper = new EntityWrapper<SysOutgoingGroup>();
        wrapper.eq("gitlab_group_name", "root-test");
        mapper.delete(wrapper);
    }
	
}
