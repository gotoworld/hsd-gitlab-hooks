package com.hsd.gitlab.systemhook.domain;

import java.util.Date;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
@Data
public class SysCommit extends IdEntity {

    private static final long serialVersionUID = 1L;
    
    private String checkoutSha;
    private String message;
    private Date commitTime;
    private String gitlabUrl;
    private Long authorId;
    private Long projectId;
    
 
    
    
}