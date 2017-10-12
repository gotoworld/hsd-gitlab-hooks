package com.hsd.gitlab.systemhook.domain;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsd.gitlab.type.IMType;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
public class SysOutgoingGroup extends IdEntity {

    private static final long serialVersionUID = -3761776409942191166L;
    
    private String gitlabGroupName;
    private IMType imType;
    private String imUrl;
    
    public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
        String s = "{ \"gitlabGroupName\": \"nameof s\",  \"id\": 0,\"imType\": \"dingtalk\",\"imUrl\": \"string\" }";
        SysOutgoingGroup page = JSON.parseObject(s, SysOutgoingGroup.class);
        System.out.println(page.getGitlabGroupName());
        
        ObjectMapper objectMapper = new ObjectMapper();
        SysOutgoingGroup acc = objectMapper.readValue(s, SysOutgoingGroup.class);
        
        System.out.println(page.getGitlabGroupName());
    }
    
    
    /**
     * @return the gitlabGroupName
     */
    public String getGitlabGroupName() {
        return gitlabGroupName;
    }
    /**
     * @param gitlabGroupName the gitlabGroupName to set
     */
    public void setGitlabGroupName(String gitlabGroupName) {
        this.gitlabGroupName = gitlabGroupName;
    }
    /**
     * @return the imType
     */
    public IMType getImType() {
        return imType;
    }
    /**
     * @param imType the imType to set
     */
    public void setImType(IMType imType) {
        this.imType = imType;
    }
    /**
     * @return the imUrl
     */
    public String getImUrl() {
        return imUrl;
    }
    /**
     * @param imUrl the imUrl to set
     */
    public void setImUrl(String imUrl) {
        this.imUrl = imUrl;
    }
    
}