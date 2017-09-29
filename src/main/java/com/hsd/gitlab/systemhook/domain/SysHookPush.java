package com.hsd.gitlab.systemhook.domain;

import com.hsd.gitlab.type.SystemEventNames;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
public class SysHookPush extends IdEntity {

    private static final long serialVersionUID = -7548741447099983168L;
    
    private String objectKind;//PS001: gitlab offical api document don't have this, but the message have it
    private SystemEventNames eventName;
    
    private String ref; //branch
    private String checkoutSha;
    private String message;//same as PS001: 
    
    private String before;
    private String after;
    
    private Long authorId;//SysAuthorID
    
    private int projectId;
    
    private Long totalCommitsCount;
    
    
    

    /**
     * @return the objectKind
     */
    public String getObjectKind() {
        return objectKind;
    }

    /**
     * @param objectKind the objectKind to set
     */
    public void setObjectKind(String objectKind) {
        this.objectKind = objectKind;
    }

    /**
     * @return the eventName
     */
    public SystemEventNames getEventName() {
        return eventName;
    }

    /**
     * @param eventName the eventName to set
     */
    public void setEventName(SystemEventNames eventName) {
        this.eventName = eventName;
    }

    /**
     * @return the ref
     */
    public String getRef() {
        return ref;
    }

    /**
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

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
     * @return the before
     */
    public String getBefore() {
        return before;
    }

    /**
     * @param before the before to set
     */
    public void setBefore(String before) {
        this.before = before;
    }

    /**
     * @return the after
     */
    public String getAfter() {
        return after;
    }

    /**
     * @param after the after to set
     */
    public void setAfter(String after) {
        this.after = after;
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
    public int getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the totalCommitsCount
     */
    public Long getTotalCommitsCount() {
        return totalCommitsCount;
    }

    /**
     * @param totalCommitsCount the totalCommitsCount to set
     */
    public void setTotalCommitsCount(Long totalCommitsCount) {
        this.totalCommitsCount = totalCommitsCount;
    }

    
    
}