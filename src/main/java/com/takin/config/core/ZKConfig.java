package com.takin.config.core;

import com.google.inject.Singleton;

@Singleton
public class ZKConfig {

    private String zkhosts;

    public String getZkhosts() {
        return zkhosts;
    }

    public void setZkhosts(String zkhosts) {
        this.zkhosts = zkhosts;
    }

}
