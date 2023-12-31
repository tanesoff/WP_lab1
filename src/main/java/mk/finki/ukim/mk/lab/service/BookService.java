package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook(Long authorId, String isbn);
    Book findBookByIsbn(String isbn);

    List<Long> getFreeIds();

    void addBook(Book book);

    Book getBookById(Long id);

    List<Book> deleteBookById(Long id);
}
