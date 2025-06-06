package service.entityService;

import dao.impls.AuthorDao;
import entity.Author;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import java.util.List;

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

    public List<Author> getAuthors() {
        return authorDao.findAll();
    }

    public Author getAuthor(Integer integer) {
        return authorDao.findById(integer);
    }

    public void addNewAuthor(HttpServletRequest req) {
        String name = req.getParameter("name");
        String concertsLink = req.getParameter("concertsLink");
        Author author = Author.builder().name(name).concertsLink(concertsLink).build();
        authorDao.save(author);
    }

    public List<Author> getAuthorsByName(String q) {
        return authorDao.getByName(q);
    }
}
