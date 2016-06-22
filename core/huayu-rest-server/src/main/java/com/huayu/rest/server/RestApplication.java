package com.huayu.rest.server;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author wangmingzhe
 * 
 */
public class RestApplication extends ResourceConfig{
    public RestApplication() {
        super(
        		MoxyXmlFeature.class,
                JacksonFeature.class
        );
    }
}