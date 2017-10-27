package com.hsd.gitlab.systemhook.domain;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
@Data
public class SysAuthor extends IdEntity {

    private static final long serialVersionUID = 1L;
    
    private Long gitlabUserId;
    private String userName;//English name
    private String name; //real name
    private String email;
    private String userAvatar;
    
    
    
}