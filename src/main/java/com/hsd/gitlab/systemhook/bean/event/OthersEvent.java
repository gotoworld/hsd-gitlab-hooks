package com.hsd.gitlab.systemhook.bean.event;
import java.util.List;

import com.hsd.gitlab.systemhook.bean.Project;
import com.hsd.gitlab.systemhook.bean.Repository;

import lombok.Data;

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
@Data
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

   
}