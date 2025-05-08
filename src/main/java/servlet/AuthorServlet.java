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

import java.io.IOException;

@WebServlet(Route.AUTHOR)
public class AuthorServlet extends HttpServlet {
    private AuthorService authorService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorService.addNewAuthor(req);
        resp.sendRedirect(Route.ADD_SONG);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authorService.getAuthorInfo(req);
        req.getRequestDispatcher(Route.getJSP(Route.AUTHOR)).forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        authorService = (AuthorService) config.getServletContext().getAttribute(ContextLoader.AUTHOR_SERVICE);
    }
}
