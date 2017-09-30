package com.hsd.gitlab.systemhook.domain;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
public class SysRPushCommit extends IdEntity {

    private static final long serialVersionUID = 5712384112258799959L;
    
    private Long pushId;
    private Long commitId;
    
    
    /**
     * @return the pushId
     */
    public Long getPushId() {
        return pushId;
    }
    /**
     * @param pushId the pushId to set
     */
    public void setPushId(Long pushId) {
        this.pushId = pushId;
    }
    /**
     * @return the commitId
     */
    public Long getCommitId() {
        return commitId;
    }
    /**
     * @param commitId the commitId to set
     */
    public void setCommitId(Long commitId) {
        this.commitId = commitId;
    }
    
}