/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.service.impl;

import org.springframework.stereotype.Service;

import com.hsd.gitlab.systemhook.service.EventHandleService;

/**
 * Class Description
 * @version Sep 28, 20176:48:58 PM
 * @author Ford.CHEN
 */
@Service("tagPushEventHandleService")
public class TagPushEventHandleServiceImpl implements EventHandleService {
    
    /* (non-Javadoc)
     * @see com.hsd.gitlab.systemhook.service.EventHandleService#handle(java.lang.String)
     */
    @Override
    public void handle(String message) {
        // TODO Auto-generated method stub
        
    }
    
}
