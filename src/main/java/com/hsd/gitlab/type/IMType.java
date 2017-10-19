package com.hsd.gitlab.type;

/**
 * 
 * Class Description
 * @version Sep 27, 20176:51:34 PM
 * @author Ford.CHEN
 */
public enum IMType {

    dingtalk("钉钉"),
    slack("Slack"),
    bearychat("倍洽"),
    weixin("微信"),
    jenkins("Jenkins");

    private String value;

    IMType(String value) {
        this.value = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return value;
    }


}
