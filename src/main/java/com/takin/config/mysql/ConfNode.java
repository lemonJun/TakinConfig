package com.takin.config.mysql;

import com.lemonjun.mysql.orm.annotation.NotDBColumn;
import com.lemonjun.mysql.orm.annotation.Table;
import com.takin.config.core.ConfZkClient;

@Table(name = "XXL_CONF_NODE")
public class ConfNode {

    private String nodeGroup; // group of prop
    private String nodeKey; // key of prop
    private String nodeValue; // value of prop
    private String nodeDesc; // description of prop

    @NotDBColumn
    private String groupKey = ""; // key of prop [in zk]
    @NotDBColumn
    private String nodeValueReal = ""; // value of prop [in zk]

    public String getNodeGroup() {
        return nodeGroup;
    }

    public void setNodeGroup(String nodeGroup) {
        this.nodeGroup = nodeGroup;
    }

    public String getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public String getGroupKey() {
        return ConfZkClient.generateGroupKey(nodeGroup, nodeKey);
    }

    public String getNodeValueReal() {
        return nodeValueReal;
    }

    public void setNodeValueReal(String nodeValueReal) {
        this.nodeValueReal = nodeValueReal;
    }

}
