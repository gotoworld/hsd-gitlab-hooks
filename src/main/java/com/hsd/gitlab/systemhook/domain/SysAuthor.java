package com.hsd.gitlab.systemhook.domain;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
public class SysAuthor extends IdEntity {

    private static final long serialVersionUID = 1L;
    
    private Long gitlabUserId;
    private String userName;//English name
    private String name; //real name
    private String email;
    private String userAvatar;
    
    
    /**
     * @return the gitlabUserId
     */
    public Long getGitlabUserId() {
        return gitlabUserId;
    }
    /**
     * @param gitlabUserId the gitlabUserId to set
     */
    public void setGitlabUserId(Long gitlabUserId) {
        this.gitlabUserId = gitlabUserId;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the userAvatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }
    /**
     * @param userAvatar the userAvatar to set
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}