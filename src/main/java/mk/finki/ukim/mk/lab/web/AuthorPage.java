package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.service.impl.AuthorServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AuthorPage", urlPatterns = "/AuthorPage")
public class AuthorPage  extends HttpServlet {

    private final BookServiceImpl bookService;
    private final AuthorServiceImpl authorService;
    private final SpringTemplateEngine springTemplateEngine;

    public AuthorPage(BookServiceImpl bookService, AuthorServiceImpl authorService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.springTemplateEngine = springTemplateEngine;
    }
    //AuthorPage?id=0
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext webContext = new WebContext(iWebExchange);
        webContext.setVariable("author", authorService.findById(Long.parseLong(req.getParameter("id"))));
        webContext.setVariable("books", bookService.getAllBooksByAuthor(Long.parseLong(req.getParameter("id"))));
        springTemplateEngine.process("authorDetails.html", webContext, resp.getWriter());

    }
}
