/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.domain;

import java.util.Date;

/**
 * Class Description
 * @version Jun 28, 20173:39:35 PM
 * @author Ford.CHEN
 */
public class AuditEntity extends IdEntity {
    private static final long serialVersionUID = -5602168898183027972L;
    
    /** 创建者ID **/
    private Long createUserId;

    /** 创建时间 **/
    private Date createTime;

    /**
     * @return the createUserId
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId the createUserId to set
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
