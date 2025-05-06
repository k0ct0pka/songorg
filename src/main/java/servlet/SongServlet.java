package servlet;

import constants.Route;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import listener.ContextLoader;
import service.entityService.AuthorService;
import service.entityService.SongService;

import java.io.IOException;

@WebServlet(Route.ADD_SONG)
public class SongServlet extends HttpServlet {
    private AuthorService authorService;
    private SongService songService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("authors",authorService.getAuthors());
        req.getRequestDispatcher(Route.getJSP(Route.ADD_SONG)).forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        authorService = (AuthorService) config.getServletContext().getAttribute(ContextLoader.AUTHOR_SERVICE);
        songService = (SongService) config.getServletContext().getAttribute(ContextLoader.SONG_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        songService.addSong(req);
    }
}
