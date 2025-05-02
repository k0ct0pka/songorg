package servlet;

import constants.Route;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listener.ContextLoader;
import service.entityService.GenreService;

import java.io.IOException;
@WebServlet(Route.GENRE)
public class GenreServlet extends HttpServlet {

    private GenreService genreService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        genreService.getSongsByGenre(req);
        req.getRequestDispatcher(Route.getJSP(Route.GENRE)).forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        genreService = (GenreService) config.getServletContext().getAttribute(ContextLoader.GENRE_SERVICE);
    }
}
