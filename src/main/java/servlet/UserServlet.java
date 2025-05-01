package servlet;

import constants.Route;
import constants.Session;
import dto.UserDto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listener.ContextLoader;
import service.LikeService;
import service.entityService.UserService;

import java.io.IOException;



@WebServlet(Route.LIKE_SONG)
public class UserServlet extends HttpServlet {

    private LikeService likeService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        likeService = (LikeService) config.getServletContext().getAttribute(ContextLoader.LIKE_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute(Session.USER.getName());
        Integer songId = Integer.valueOf(req.getParameter("songId"));
        likeService.likeSong(user.getId(),songId);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Route.getJSP(Route.LIKE_SONG)).forward(req, resp);
    }
}
