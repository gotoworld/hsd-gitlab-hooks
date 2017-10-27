package com.hsd.gitlab.systemhook.domain;

import java.util.Date;

import com.hsd.gitlab.type.SystemEventNames;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
@Data
public class SysHookPush extends IdEntity {

    private static final long serialVersionUID = -7548741447099983168L;
    
    private String objectKind;//PS001: gitlab offical api document don't have this, but the message have it
    private SystemEventNames eventName;
    
    private String ref; //branch
    private String checkoutSha;
    private String message;//same as PS001: 
    
    private String before;
    private String after;
    
    private Long authorId;//SysAuthorID
    
    private Long projectId;
    
    private Long totalCommitsCount;
    
    private Date eventTime;
    
    

   
    
}