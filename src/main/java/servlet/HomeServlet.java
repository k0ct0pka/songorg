package servlet;

import constants.Route;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listener.ContextLoader;
import service.HomeService;
import service.entityService.SongService;

import java.io.IOException;

@WebServlet(Route.HOME)
public class HomeServlet extends HttpServlet {

    private HomeService homeService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        homeService = (HomeService) config.getServletContext().getAttribute(ContextLoader.HOME_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        homeService.initiateHomePage(req);
        req.getRequestDispatcher(Route.getJSP(Route.HOME)).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

}
