package com.hsd.gitlab.systemhook.bean;

/**
 * 
 * Class Description
 * @version Sep 27, 201711:54:50 AM
 * @author Ford.CHEN
 */
public class Changes {

    private String before;
    private String after;
    private String ref;
    
    
    /**
     * @return the before
     */
    public String getBefore() {
        return before;
    }
    /**
     * @param before the before to set
     */
    public void setBefore(String before) {
        this.before = before;
    }
    /**
     * @return the after
     */
    public String getAfter() {
        return after;
    }
    /**
     * @param after the after to set
     */
    public void setAfter(String after) {
        this.after = after;
    }
    /**
     * @return the ref
     */
    public String getRef() {
        return ref;
    }
    /**
     * @param ref the ref to set
     */
    public void setRef(String ref) {
        this.ref = ref;
    }

    
    
}