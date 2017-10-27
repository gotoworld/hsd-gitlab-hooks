package com.hsd.gitlab.systemhook.bean;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:45:31 AM
 * @author Ford.CHEN
 */
@Data
public class Commits {

    private String id;
    private String message;
    private Date timestamp;
    private String url;
    
    private List<String> added;//PS001: gitlab offical api document don't have this, but the message have it
    private List<String> modified;//PS001: gitlab offical api document don't have this, but the message have it
    private List<String> removed;//PS001: gitlab offical api document don't have this, but the message have it
    
    private Author author;

}