package com.hsd.gitlab.systemhook.domain;

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