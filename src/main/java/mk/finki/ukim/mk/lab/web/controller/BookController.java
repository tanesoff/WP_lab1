package mk.finki.ukim.mk.lab.web.controller;



import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.DTO.KinzarnicaNameId;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.impl.BookServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.BookStoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    private final BookServiceImpl bookService;
    private final BookStoreServiceImpl bookStoreService;

    public BookController(BookServiceImpl bookService, BookStoreServiceImpl bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping("/books")
    public String getBooksPage(@RequestParam(required = false) String error, Model model){

        model.addAttribute("books", bookService.listBooks());

        return "listBookController.html";
    }

    @GetMapping("/books/add")
    public String saveBook(Model model){
        List<KinzarnicaNameId> kinzarnicaNameIds = bookStoreService.kinzarnicaNameId();
        model.addAttribute("knizarnici", kinzarnicaNameIds);
        return "addBook.html";
    }
    @PostMapping("/books/add")
    public RedirectView saveBookAdd(String title, String isbn, String genre, String year, String id, String bookID){
        if(bookID != null)
            bookService.deleteBookById(Long.parseLong(bookID));
        Book book = new Book(isbn, title, genre, Integer.parseInt(year));
        book.setBookStore(bookStoreService.getBookStoreById(Long.parseLong(id)));
        bookService.addBook(book);
        return new RedirectView("/books");
    }

    @GetMapping("/books/edit/{bookId}")
    public String editBook(@PathVariable Long bookId, Model model){
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        model.addAttribute("knizarnici", bookStoreService.kinzarnicaNameId());
        return "addBook.html";
    }

    @GetMapping("/books/delete/{id}")
    public RedirectView deleteBook(@PathVariable Long id){
        bookService.deleteBookById(id);
        return new RedirectView("/books");
    }
}
