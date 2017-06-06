package cn.edu.rest;

import cn.edu.utils.rest.AbstractWebApplication;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * Created by tanlong on 2017/6/6.
 */
public class CoreWebApplication extends AbstractWebApplication {
    public Set<Class<?>> getClasses() {
        return ImmutableSet.<Class<?>>builder()
                .add(HelloWebResource.class)
                .build();
    }
}
