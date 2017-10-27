package com.hsd.gitlab.systemhook.bean;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:54:50 AM
 * @author Ford.CHEN
 */
@Data
public class Changes {

    private String before;
    private String after;
    private String ref;
    
}