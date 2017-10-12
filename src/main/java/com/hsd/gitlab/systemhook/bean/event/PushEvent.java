package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import com.hsd.gitlab.systemhook.bean.Commits;
import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.Repository;

/**
 *
 *
 *{
 *    "object_kind":"push",
 *    "event_name":"push",
 *    "before":"beb29d6aae20e17f44f68235c1d9534ccd871277",
 *    "after":"a54866513772560b9120b268c00f6cafe565bab8",
 *    "ref":"refs/heads/abranch",
 *    "checkout_sha":"a54866513772560b9120b268c00f6cafe565bab8",
 *    "message":null,
 *    "user_id":1,
 *    "user_name":"Administrator",
 *    "user_username":"root",
 *    "user_email":"admin@example.com",
 *    "user_avatar":"http://www.gravatar.com/avatar/e64c7d89f26bd1972efa854d13d7dd61?s=80&d=identicon",
 *    "project_id":1,
 *    "project":{
 *        "name":"ss",
 *        "description":"",
 *        "web_url":"http://192.168.254.205/infrastructure/ss",
 *        "avatar_url":null,
 *        "git_ssh_url":"git@192.168.254.205:infrastructure/ss.git",
 *        "git_http_url":"http://192.168.254.205/infrastructure/ss.git",
 *        "namespace":"infrastructure",
 *        "visibility_level":0,
 *        "path_with_namespace":"infrastructure/ss",
 *        "default_branch":"master",
 *        "ci_config_path":null,
 *        "homepage":"http://192.168.254.205/infrastructure/ss",
 *        "url":"git@192.168.254.205:infrastructure/ss.git",
 *        "ssh_url":"git@192.168.254.205:infrastructure/ss.git",
 *        "http_url":"http://192.168.254.205/infrastructure/ss.git"
 *    },
 *    "commits":[
 *        {
 *            "id":"a54866513772560b9120b268c00f6cafe565bab8",
 *            "message":"add
 *",
 *            "timestamp":"2017-09-29T12:45:53+08:00",
 *            "url":"http://192.168.254.205/infrastructure/ss/commit/a54866513772560b9120b268c00f6cafe565bab8",
 *            "author":{
 *                "name":"chenfangri",
 *                "email":"chenfangri@heshidai.com"
 *            },
 *            "added":[
 *                "aaa11.tx"
 *            ],
 *            "modified":[
 *
 *            ],
 *            "removed":[
 *
 *            ]
 *        }
 *    ],
 *    "total_commits_count":1,
 *    "repository":{
 *        "name":"ss",
 *        "url":"git@192.168.254.205:infrastructure/ss.git",
 *        "description":"",
 *        "homepage":"http://192.168.254.205/infrastructure/ss",
 *        "git_http_url":"http://192.168.254.205/infrastructure/ss.git",
 *        "git_ssh_url":"git@192.168.254.205:infrastructure/ss.git",
 *        "visibility_level":0
 *    }
 *}
 *
 *
 * Class Description
 * @version Sep 27, 201711:37:39 AM
 * @author Ford.CHEN
 */
public class PushEvent extends BaseEvent {

    private String objectKind;//PS001: gitlab offical api document don't have this, but the message have it
    
    private String ref; //branch
    private String checkoutSha;
    private String message;//same as PS001: 
    
    
    private String before;
    private String after;
    
    
    private Long userId;
    private String userUsername; //ford， same as PS001: 
    private String username; //陈
    private String userEmail;
    private String userAvatar;
    
    
    private int projectId;
    private Project project;
    private Repository repository;
    
    private List<Commits> commits;
    
    private Long totalCommitsCount;
    
    
    /**
     * 
     * Method Description
     * @version Oct 9, 201711:45:10 AM
     * @author Ford.CHEN
     * @return
     */
    public String toDingTalkMarkdown(){
        
       StringBuffer sb = new StringBuffer();
       sb.append("{ \"msgtype\": \"markdown\",   \"markdown\":          {\"title\": \"gitlab push event\", \"text\" : \"");

       String branch = "";
       String[] s = ref.split("\\/");
       if(s.length == 3){
           branch = s[2];
       }
       
       String title = "#### " + username + " pushed to branch " + branch + " at repository " + project.getName()+ " \n";
       sb.append(title);
       
       
       String content= ""; 
       for(Commits commit : commits){
//           String cid = commit.getId().substring(0, 7);
           
           DateTime dateTime = new DateTime(commit.getTimestamp());
//           content = content + " > [" + commit.getMessage() + "](" + commit.getUrl() + "), " + commit.getAuthor().getName() + ", " + dateTime.toString("MM/dd HH:mm EE",Locale.ENGLISH) +  "\\n\\n   ";
           content = content + " > [" + commit.getMessage() + "](" + commit.getUrl() + "), " + commit.getAuthor().getName() + ", " + dateTime.toString("HH:mm EE",Locale.ENGLISH) +  "\\n\\n   ";
       }
       sb.append(content);
       
       sb.append(" \"}}");
        
        return sb.toString();
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
    public List<Commits> getCommits() {
        return commits;
    }

    /**
     * @param commits the commits to set
     */
    public void setCommits(List<Commits> commits) {
        this.commits = commits;
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
     * @return the userUsername
     */
    public String getUserUsername() {
        return userUsername;
    }

    /**
     * @param userUsername the userUsername to set
     */
    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

}