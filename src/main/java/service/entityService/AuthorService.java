package service.entityService;

import dao.impls.AuthorDao;
import entity.Author;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorService {
    private AuthorDao authorDao;
    public void getAuthorInfo(HttpServletRequest req) {
        Integer autId = Integer.valueOf(req.getParameter("aut_id"));
        Author byId = authorDao.findById(autId);
        Long count = authorDao.countListeners(autId);
        req.setAttribute("author", byId);
        req.setAttribute("countListeners", count);
    }
}
