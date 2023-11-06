package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "AuthorServlet", urlPatterns = "/author")
public class АuthorServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final BookServiceImpl bookService;

    public АuthorServlet(SpringTemplateEngine springTemplateEngine, BookServiceImpl bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext webContext = new WebContext(webExchange);
        webContext.setVariable("book", bookService.findBookByIsbn(req.getParameter("bookIsbn")));
        webContext.setVariable("authors", bookService.getAllAuthorsThatAreNotInTheBook(req.getParameter("bookIsbn")));
        springTemplateEngine.process("authorList.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String isbn = req.getParameter("isbn");
        String authorId = req.getParameter("authorId");
        bookService.addAuthorToBook(Long.parseLong(authorId), isbn);
        resp.sendRedirect("/bookDetails?bookIsbn=" + isbn);
    }
}
