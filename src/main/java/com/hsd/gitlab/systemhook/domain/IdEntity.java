/*
 * Copyright 2017-2020 the original author: Ford.CHEN
 *
 */
package com.hsd.gitlab.systemhook.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Class Description
 * @version Jun 28, 20172:50:46 PM
 * @author Ford.CHEN
 */
public class IdEntity implements Serializable {
    
    private static final long serialVersionUID = 8295687780528537216L;

    @TableId(type=IdType.AUTO)
    private Long id;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
