package org.geektimes.projects.user.web.controller;

import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Path;
import org.geektimes.context.ComponentContext;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.web.mvc.controller.PageController;

@Path("/register")
public class UserRegisterController implements PageController {

    @Resource
    private UserService userService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
        throws Throwable {
        userService = ComponentContext.getInstance().getComponent("bean/UserService");
        String inputEmail = request.getParameter("inputEmail");
        String inputPassword = request.getParameter("inputPassword");
        String inputPhone = request.getParameter("inputPhone");
        String inputName = request.getParameter("inputName");

        User newUser = new User();
        newUser.setEmail(inputEmail);
        newUser.setPhoneNumber(inputPhone);
        newUser.setName(inputName);
        newUser.setPassword(inputPassword);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        // 手动校验
        Set<ConstraintViolation<User>> violations = validator.validate(newUser);

        ServletContext servletContext = request.getServletContext();

        if (userService.register(newUser)) {
            // success
            RequestDispatcher rd = ((ServletContext) servletContext).getRequestDispatcher("/success.jsp");
            rd.forward(request, response);
        } else {
            // success
            RequestDispatcher rd = ((ServletContext) servletContext).getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
        }
        return "";
    }
}
