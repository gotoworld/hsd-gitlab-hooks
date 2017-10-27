package com.hsd.gitlab.systemhook.domain;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:49:07 AM
 * @author Ford.CHEN
 */
@Data
public class SysProject extends IdEntity {

    private static final long serialVersionUID = 1118465127791269444L;
    
    private String name;
    private String webUrl;
    private String avatarUrl;
    private String gitSshUrl;
    private String gitHttpUrl;
//    private String gitlabGroupId;
    private String groupName;//  /gold
    private String pathWithNamespace; //   /gold/pay_console
    
}