/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.service;

/**
 * Class Description
 * @version Sep 28, 20175:19:05 PM
 * @author Ford.CHEN
 */
public interface EventHandleService {
    
    /**
     * 
     * Method Description
     * @version Sep 28, 20176:38:04 PM
     * @author Ford.CHEN
     * @param message
     */
    public void handle(String message);
    
    
    
}
