package org.geektimes.configuration.microprofile.config;

import javax.servlet.ServletContext;

/**
 * @author valora
 */
public class ConfigContext {

    public static final String DEFALUT = "default_config";

    private static ServletContext servletContext;

    private DefaultConfig config;

    public void init(ServletContext servletContext, DefaultConfig config) {
        ConfigContext.servletContext = servletContext;
        this.config = config;
        servletContext.setAttribute(DEFALUT, this);
    }
    public static ConfigContext getInstance() {
        return (ConfigContext) servletContext.getAttribute(DEFALUT);
    }
    public DefaultConfig getConfig() {
        return config;
    }

}
