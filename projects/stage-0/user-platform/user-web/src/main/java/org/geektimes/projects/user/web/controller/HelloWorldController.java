package org.geektimes.projects.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.geektimes.configuration.microprofile.config.ConfigContext;
import org.geektimes.web.mvc.controller.PageController;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/hello")
public class HelloWorldController implements PageController {

    @Override
    @GET
    @POST
    @Path("/world") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        Config defaultConfig = ConfigContext.getInstance().getConfig();
        ConfigValue configValue = defaultConfig.getConfigValue("test");
        System.out.println(configValue.getName());
        System.out.println(configValue.getValue());
        System.out.println(configValue.getSourceName());
        return "index.jsp";
    }
}
