package com.hsd.gitlab.systemhook.bean;
import java.util.Date;
import java.util.List;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:45:31 AM
 * @author Ford.CHEN
 */
public class Commits {

    private String id;
    private String message;
    private Date timestamp;
    private String url;
    
    private List<String> added;//PS001: gitlab offical api document don't have this, but the message have it
    private List<String> modified;//PS001: gitlab offical api document don't have this, but the message have it
    private List<String> removed;//PS001: gitlab offical api document don't have this, but the message have it
    
    private Author author;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the added
     */
    public List<String> getAdded() {
        return added;
    }

    /**
     * @param added the added to set
     */
    public void setAdded(List<String> added) {
        this.added = added;
    }

    /**
     * @return the modified
     */
    public List<String> getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(List<String> modified) {
        this.modified = modified;
    }

    /**
     * @return the removed
     */
    public List<String> getRemoved() {
        return removed;
    }

    /**
     * @param removed the removed to set
     */
    public void setRemoved(List<String> removed) {
        this.removed = removed;
    }
    
    
    

}