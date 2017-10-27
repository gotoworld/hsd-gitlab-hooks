/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.bean.event;

import com.alibaba.fastjson.JSON;
import com.hsd.gitlab.type.SystemEventNames;

import lombok.Data;

/**
 * Class Description
 * @version Sep 27, 20171:31:59 PM
 * @author Ford.CHEN
 */
@Data
public class BaseEvent extends AbstractEvent {
    
    protected SystemEventNames eventName;
    
    

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
