package filter;

import constants.Route;
import constants.Session;
import dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import listener.ContextLoader;
import java.util.*;
import service.entityService.UserService;

import java.io.IOException;
@WebFilter({"/like-song"})
public class DataRefreshFilter implements Filter {
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = (UserService) filterConfig.getServletContext().getAttribute(ContextLoader.USER_SERVICE);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        UserDto user = (UserDto) req.getSession().getAttribute(Session.USER.getName());
        user = userService.refreshUserData(user);
        req.getSession().setAttribute(Session.USER.getName(), user);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
