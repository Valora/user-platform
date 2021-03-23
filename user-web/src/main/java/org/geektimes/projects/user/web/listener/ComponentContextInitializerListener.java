package org.geektimes.projects.user.web.listener;

import java.lang.management.ManagementFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.geektimes.context.ComponentContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.geektimes.projects.user.manage.Author;

/**
 * {@link ComponentContext} 初始化器
 * ContextLoaderListener
 */
public class ComponentContextInitializerListener implements ServletContextListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.servletContext = sce.getServletContext();
        ComponentContext context = new ComponentContext();
        context.init(servletContext);
        registerAuthorMXBean();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        ComponentContext context = ComponentContext.getInstance();
//        context.destroy();
        unregisterAuthorMXBean();
    }

    private void registerAuthorMXBean() {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName objectName = new ObjectName("org.geektimes.project.user.management:type=Author");
            Author author = Author.getInstance();
            platformMBeanServer.registerMBean(author, objectName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void unregisterAuthorMXBean() {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName objectName = new ObjectName("org.geektimes.project.user.management:type=Author");
            platformMBeanServer.unregisterMBean(objectName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
