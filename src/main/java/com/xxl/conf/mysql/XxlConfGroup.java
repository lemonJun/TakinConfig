package com.xxl.conf.mysql;

import com.bj58.sfft.utility.dao.annotation.Table;
import com.bj58.spat.scf.serializer.component.annotation.SCFSerializable;

/**
 * Created by xuxueli on 16/10/8.
 */

@Table(name = "XXL_CONF_GROUP")
@SCFSerializable
public class XxlConfGroup {

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
