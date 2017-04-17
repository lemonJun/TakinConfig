package com.xxl.conf.util;

import com.google.inject.AbstractModule;

public class BindingModel extends AbstractModule {

    @Override
    protected void configure() {
        //        bindInterceptor(Matchers.inSubpackage("com.bj58.chr.sm.wf.provider"), Matchers.any(), new StatMethod());
    }
}
