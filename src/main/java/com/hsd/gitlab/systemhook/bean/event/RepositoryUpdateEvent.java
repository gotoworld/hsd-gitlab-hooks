package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;

import com.hsd.gitlab.systemhook.bean.Changes;
import com.hsd.gitlab.systemhook.bean.Project;

/**
 * 
 *  *{
 *  "event_name": "repository_update",
 *  "user_id": 1,
 *  "user_name": "John Smith",
 *  "user_email": "admin@example.com",
 *  "user_avatar": "https://s.gravatar.com/avatar/d4c74594d841139328695756648b6bd6?s=8://s.gravatar.com/avatar/d4c74594d841139328695756648b6bd6?s=80",
 *  "project_id": 1,
 *  "project": {
 *    "name":"Example",
 *    "description":"",
 *    "web_url":"http://example.com/jsmith/example",
 *    "avatar_url":null,
 *    "git_ssh_url":"git@example.com:jsmith/example.git",
 *    "git_http_url":"http://example.com/jsmith/example.git",
 *    "namespace":"Jsmith",
 *    "visibility_level":0,
 *    "path_with_namespace":"jsmith/example",
 *    "default_branch":"master",
 *    "homepage":"http://example.com/jsmith/example",
 *    "url":"git@example.com:jsmith/example.git",
 *    "ssh_url":"git@example.com:jsmith/example.git",
 *    "http_url":"http://example.com/jsmith/example.git",
 *  },
 *  "changes": [
 *    {
 *      "before":"8205ea8d81ce0c6b90fbe8280d118cc9fdad6130",
 *      "after":"4045ea7a3df38697b3730a20fb73c8bed8a3e69e",
 *      "ref":"refs/heads/master"
 *    }
 *  ],
 *  "refs":["refs/heads/master"]
 *}
 *
 * 
 * Class Description
 * @version Sep 27, 201711:55:40 AM
 * @author Ford.CHEN
 */
public class RepositoryUpdateEvent extends BaseEvent {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userAvatar;
    private int projectId;
    
    private Project project;
    private List<Changes> changes;
    
    private List<String> refs;

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
     * @return the userEmail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * @param userEmail the userEmail to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
     * @return the changes
     */
    public List<Changes> getChanges() {
        return changes;
    }

    /**
     * @param changes the changes to set
     */
    public void setChanges(List<Changes> changes) {
        this.changes = changes;
    }

    /**
     * @return the refs
     */
    public List<String> getRefs() {
        return refs;
    }

    /**
     * @param refs the refs to set
     */
    public void setRefs(List<String> refs) {
        this.refs = refs;
    }
    

}