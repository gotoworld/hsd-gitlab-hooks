package com.hsd.gitlab.systemhook.domain;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
public class SysProject extends IdEntity {

    private static final long serialVersionUID = 1118465127791269444L;
    
    private String name;
    private String webUrl;
    private String avatarUrl;
    private String gitSshUrl;
    private String gitHttpUrl;
//    private String gitlabGroupId;
    private String groupName;//  /gold
    private String pathWithNamespace; //   /gold/pay_console
    
    
    
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
     * @return the webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }
    /**
     * @param webUrl the webUrl to set
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
    /**
     * @return the avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }
    /**
     * @param avatarUrl the avatarUrl to set
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    /**
     * @return the gitSshUrl
     */
    public String getGitSshUrl() {
        return gitSshUrl;
    }
    /**
     * @param gitSshUrl the gitSshUrl to set
     */
    public void setGitSshUrl(String gitSshUrl) {
        this.gitSshUrl = gitSshUrl;
    }
    /**
     * @return the gitHttpUrl
     */
    public String getGitHttpUrl() {
        return gitHttpUrl;
    }
    /**
     * @param gitHttpUrl the gitHttpUrl to set
     */
    public void setGitHttpUrl(String gitHttpUrl) {
        this.gitHttpUrl = gitHttpUrl;
    }

    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * @return the pathWithNamespace
     */
    public String getPathWithNamespace() {
        return pathWithNamespace;
    }
    /**
     * @param pathWithNamespace the pathWithNamespace to set
     */
    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }

    
    
}