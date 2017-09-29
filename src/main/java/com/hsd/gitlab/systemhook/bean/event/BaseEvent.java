/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.bean.event;

import com.alibaba.fastjson.JSON;
import com.hsd.gitlab.type.SystemEventNames;

/**
 * Class Description
 * @version Sep 27, 20171:31:59 PM
 * @author Ford.CHEN
 */
public class BaseEvent extends AbstractEvent {
    
    protected SystemEventNames eventName;
    
    

    /**
     * @return the eventName
     */
    public SystemEventNames getEventName() {
        return eventName;
    }




    /**
     * @param eventName the eventName to set
     */
    public void setEventName(SystemEventNames eventName) {
        this.eventName = eventName;
    }
    

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
