package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import mk.finki.ukim.mk.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookDetailsSerlet", urlPatterns = "/bookDetails")
public class bookDetailsServlet  extends HttpServlet {
    BookServiceImpl bookService;
    SpringTemplateEngine springTemplateEngine;

    public bookDetailsServlet(BookServiceImpl bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext webContext = new WebContext(iWebExchange);
        String bookIsbn = req.getParameter("bookIsbn");
        webContext.setVariable("book",bookService.findBookByIsbn(bookIsbn));
        springTemplateEngine.process("bookDetails.html", webContext, resp.getWriter());
    }
}
