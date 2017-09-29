package com.hsd.gitlab.type;

import com.hsd.gitlab.systemhook.bean.event.AbstractEvent;
import com.hsd.gitlab.systemhook.bean.event.OthersEvent;
import com.hsd.gitlab.systemhook.bean.event.PushEvent;
import com.hsd.gitlab.systemhook.bean.event.RepositoryUpdateEvent;
import com.hsd.gitlab.systemhook.bean.event.TagPushEvent;

/**
 * 
 * Class Description
 * @version Sep 27, 20176:51:34 PM
 * @author Ford.CHEN
 */
public enum SystemEventNames {

    push(PushEvent.class),
    tag_push(TagPushEvent.class),
    repository_update(RepositoryUpdateEvent.class), //only support by gitlab EE
    others_event(OthersEvent.class);

    private Class<? extends AbstractEvent> type;

    SystemEventNames(Class<? extends AbstractEvent> type) {
        this.type = type;
    }

    public Class<? extends AbstractEvent> getType() {
        return type;
    }
}
