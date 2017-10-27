package com.hsd.gitlab.systemhook.domain;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
@Data
public class SysRPushCommit extends IdEntity {

    private static final long serialVersionUID = 5712384112258799959L;
    
    private Long pushId;
    private Long commitId;
    
    
    
}