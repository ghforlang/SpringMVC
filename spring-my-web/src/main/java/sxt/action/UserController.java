package sxt.action;

import sxt.service.IUserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenovo on 2017/1/10.
 */
public class UserController implements Controller {

    private IUserService userService;

    @Override
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse httpServletResponse) throws Exception {
        System.out.println("HelloController.handleRequest()");
        req.setAttribute("a", "aaaa");
        userService.add(req.getParameter("uname"));
        return new ModelAndView("index");
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
