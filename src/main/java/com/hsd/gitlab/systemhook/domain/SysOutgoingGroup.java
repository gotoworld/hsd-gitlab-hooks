package com.hsd.gitlab.systemhook.domain;

import com.hsd.gitlab.type.IMType;
import com.hsd.gitlab.type.SystemEventNames;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
@Data
public class SysOutgoingGroup extends IdEntity {

    private static final long serialVersionUID = -3761776409942191166L;
    
    private String gitlabGroupName;
    private IMType imType;
    private String imUrl;
    
    private String name;
    private String description;
    
    private SystemEventNames event;
    
    
    
    
    
}