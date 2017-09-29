package com.hsd.gitlab.systemhook.domain;

import java.util.Date;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
public class SysCommit extends IdEntity {

    private static final long serialVersionUID = 1L;
    
    private String checkoutSha;
    private String message;
    private Date commitTime;
    private String gitlabUrl;
    private Long authorId;
    private Long projectId;
    
 
    /**
     * @return the checkoutSha
     */
    public String getCheckoutSha() {
        return checkoutSha;
    }
    /**
     * @param checkoutSha the checkoutSha to set
     */
    public void setCheckoutSha(String checkoutSha) {
        this.checkoutSha = checkoutSha;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the commitTime
     */
    public Date getCommitTime() {
        return commitTime;
    }
    /**
     * @param commitTime the commitTime to set
     */
    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }
    /**
     * @return the gitlabUrl
     */
    public String getGitlabUrl() {
        return gitlabUrl;
    }
    /**
     * @param gitlabUrl the gitlabUrl to set
     */
    public void setGitlabUrl(String gitlabUrl) {
        this.gitlabUrl = gitlabUrl;
    }
    /**
     * @return the authorId
     */
    public Long getAuthorId() {
        return authorId;
    }
    /**
     * @param authorId the authorId to set
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
    /**
     * @return the projectId
     */
    public Long getProjectId() {
        return projectId;
    }
    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    
}