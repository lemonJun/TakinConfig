package com.xxl.conf.mysql;

import com.bj58.sfft.utility.dao.annotation.Table;

@Table(name = "XXL_CONF_GROUP")
public class ConfGroup {

    private String groupName;
    private String groupTitle;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
}
