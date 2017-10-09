package com.hsd.gitlab.systemhook.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.hsd.gitlab.systemhook.service.EventHandleService;
import com.hsd.gitlab.type.SystemEventNames;

/**
 * 
 * Class Description
 * @version Sep 28, 20177:17:48 PM
 * @author Ford.CHEN
 */
@Component
public class EventHandleServiceFactory implements ApplicationContextAware {
    
    private static Map<SystemEventNames, EventHandleService> eventHandleServiceMap;
    private static Map<String,SystemEventNames> serviceNameToEnumMap = new HashMap<String,SystemEventNames>();
    
    static {
        serviceNameToEnumMap.put("pushEventHandleService", SystemEventNames.push); 
        serviceNameToEnumMap.put("tagPushEventHandleService", SystemEventNames.tag_push); 
        serviceNameToEnumMap.put("othersEventHandleService", SystemEventNames.repository_update); 
        serviceNameToEnumMap.put("othersEventHandleService", SystemEventNames.others_event); 
    }
    
    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        eventHandleServiceMap = new HashMap<SystemEventNames, EventHandleService>();
        
        Map<String, EventHandleService> map = applicationContext.getBeansOfType(EventHandleService.class);
        map.forEach((key, value) -> eventHandleServiceMap.put(serviceNameToEnumMap.get(key), value));
    }
    
    /**
     * 
     * Method Description
     * @version Sep 28, 20177:17:24 PM
     * @author Ford.CHEN
     * @param code
     * @return
     */
    public <T extends EventHandleService> T getEventHandleService(SystemEventNames code) {
        return (T) eventHandleServiceMap.get(code);
    }
}
