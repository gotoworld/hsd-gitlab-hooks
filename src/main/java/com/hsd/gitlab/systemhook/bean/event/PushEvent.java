package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import com.hsd.gitlab.systemhook.bean.Commits;
import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.Repository;

import lombok.Data;

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
@Data
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
     * 
     * payload={"text": "A very important thing has occurred! <https://alert-system.com/alerts/1234|Click here> for details!"}
     * @version Oct 30, 20179:15:07 AM
     * @author Ford.CHEN
     * @return
     */
    public String toSlackJson(){
        
       StringBuffer sb = new StringBuffer();
       sb.append("{\"text\": \"");
        
       String branch = "";
       String[] s = ref.split("\\/");
       if(s.length == 3){
           branch = s[2];
       }
       
       String title = "" + username + " pushed to branch " + branch + " at repository " + project.getName()+ " \n";
       sb.append(title);
       
       
       String content= ""; 
       for(Commits commit : commits){
           DateTime dateTime = new DateTime(commit.getTimestamp());
           content = content + "<" + commit.getUrl() + "|" + commit.getMessage() + "> , " + commit.getAuthor().getName() + ", " + dateTime.toString("HH:mm EE",Locale.ENGLISH) +  "\n   ";
       }
       sb.append(content);
       
       sb.append(" \"}");
        
        return sb.toString();
    }
    
}