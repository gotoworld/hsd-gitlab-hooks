package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;

import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.Repository;

import lombok.Data;

/**
 * 
 *{
 *  "event_name": "tag_push",
 *  "before": "0000000000000000000000000000000000000000",
 *  "after": "82b3d5ae55f7080f1e6022629cdb57bfae7cccc7",
 *  "ref": "refs/tags/v1.0.0",
 *  "checkout_sha": "5937ac0a7beb003549fc5fd26fc247adbce4a52e",
 *  "user_id": 1,
 *  "user_name": "John Smith",
 *  "user_avatar": "https://s.gravatar.com/avatar/d4c74594d841139328695756648b6bd6?s=8://s.gravatar.com/avatar/d4c74594d841139328695756648b6bd6?s=80",
 *  "project_id": 1,
 *  "project":{
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
 *    "http_url":"http://example.com/jsmith/example.git"
 *  },
 *  "repository":{
 *    "name": "Example",
 *    "url": "ssh://git@example.com/jsmith/example.git",
 *    "description": "",
 *    "homepage": "http://example.com/jsmith/example",
 *    "git_http_url":"http://example.com/jsmith/example.git",
 *    "git_ssh_url":"git@example.com:jsmith/example.git",
 *    "visibility_level":0
 *  },
 *  "commits": [],
 *  "total_commits_count": 0
 *}
 *
 * 
 * Class Description
 * @version Sep 27, 201711:56:57 AM
 * @author Ford.CHEN
 */
@Data
public class TagPushEvent extends BaseEvent {

    private String before;
    private String after;
    private String ref;
    private String checkoutSha;
    private Long userId;
    private String userName;//陈
    private String userAvatar;
    private int projectId;
    
    private Project project;
    private Repository repository;
    
    private List<String> commits;
    
    private int totalCommitsCount;
    
    //new version add
    private String objectKind;//PS001: gitlab offical api document don't have this, but the message have it
    private String message;//same as PS001: 
    private String userUsername; //ford， same as PS001: 
    private String userEmail;//same as PS001:
    
    
    
    /**
     * 
     * Method Description
     * @version Oct 9, 201711:45:10 AM
     * @author Ford.CHEN
     * @return
     */
    public String toDingTalkMarkdown(){
        
       StringBuffer sb = new StringBuffer();
       sb.append("{ \"msgtype\": \"markdown\",   \"markdown\":          {\"title\": \"gitlab tag event\", \"text\" : \"");

       String tag = "";
       String[] s = ref.split("\\/");
       if(s.length == 3){
           tag = s[2];
       }
       
       String title = "#### " + userName + "  published  " + project.getName()+ ":" + tag + " , at " + (new DateTime()).toString("MM/dd HH:mm EE",Locale.ENGLISH) + " \n";
       sb.append(title);
       
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
        
       String tag = "";
       String[] s = ref.split("\\/");
       if(s.length == 3){
           tag = s[2];
       }
       
       String title = userName + "  published  " + project.getName()+ ":" + tag + " , at " + (new DateTime()).toString("MM/dd HH:mm EE",Locale.ENGLISH) + " \n";
       sb.append(title);
       
       sb.append(" \n\n\n\n\"} ");
       
        return sb.toString();
    }
    
    /**
     * 
     * to slack, interactive message with button
     * 
     *{
     *    "text": "Would you like to play a game?",
     *    "attachments": [
     *        {
     *            "text": "Choose a game to play",
     *            "fallback": "You are unable to choose a game",
     *            "callback_id": "wopr_game",
     *            "color": "#3AA3E3",
     *            "attachment_type": "default",
     *            "actions": [
     *                {
     *                    "name": "game",
     *                    "text": "Confirm",
     *                    "type": "button",
     *                    "value": "confirm"
     *                },
     *                {
     *                    "name": "game",
     *                    "text": "Quit",
     *                    "type": "button",
     *                    "value": "quit"
     *                },
     *                {
     *                    "name": "game",
     *                    "text": "Cancel",
     *                    "style": "danger",
     *                    "type": "button",
     *                    "value": "cancel",
     *                    "confirm": {
     *                        "title": "Are you sure?",
     *                        "text": "Wouldn't you prefer a good game of chess?",
     *                        "ok_text": "Yes",
     *                        "dismiss_text": "No"
     *                    }
     *                }
     *            ]
     *        }
     *    ]
     *} 
     * 
     */
    
}