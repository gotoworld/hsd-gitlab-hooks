package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;

import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.Repository;

/**
 * 
 *
 * the following events: project_create, project_destroy, project_rename, project_transfer, 
 * project_update, user_add_to_team, user_remove_from_team, user_create, user_destroy, key_create, 
 * key_destroy, group_create, group_destroy, user_add_to_group and user_remove_from_group.
 *
 * 
 * Class Description
 * @version Sep 27, 201711:56:57 AM
 * @author Ford.CHEN
 */
public class OthersEvent extends BaseEvent {

    private String before;
    private String after;
    private String ref;
    private String checkoutSha;
    private Long userId;
    private String userName;
    private String userAvatar;
    private int projectId;
    
    private Project project;
    private Repository repository;
    
    private List<String> commits;
    
    private int totalCommitsCount;

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
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project the project to set
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * @return the repository
     */
    public Repository getRepository() {
        return repository;
    }

    /**
     * @param repository the repository to set
     */
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    /**
     * @return the commits
     */
    public List<String> getCommits() {
        return commits;
    }

    /**
     * @param commits the commits to set
     */
    public void setCommits(List<String> commits) {
        this.commits = commits;
    }

    /**
     * @return the totalCommitsCount
     */
    public int getTotalCommitsCount() {
        return totalCommitsCount;
    }

    /**
     * @param totalCommitsCount the totalCommitsCount to set
     */
    public void setTotalCommitsCount(int totalCommitsCount) {
        this.totalCommitsCount = totalCommitsCount;
    }
    

}