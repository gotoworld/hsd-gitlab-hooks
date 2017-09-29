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
    private String im_url;
    
    
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
     * @return the im_url
     */
    public String getIm_url() {
        return im_url;
    }
    /**
     * @param im_url the im_url to set
     */
    public void setIm_url(String im_url) {
        this.im_url = im_url;
    }
    


    
    
}