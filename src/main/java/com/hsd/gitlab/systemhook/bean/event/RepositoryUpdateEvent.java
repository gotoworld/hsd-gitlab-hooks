package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;

import com.hsd.gitlab.systemhook.bean.Changes;
import com.hsd.gitlab.systemhook.bean.Project;

import lombok.Data;

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
@Data
public class RepositoryUpdateEvent extends BaseEvent {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userAvatar;
    private int projectId;
    
    private Project project;
    private List<Changes> changes;
    
    private List<String> refs;

}