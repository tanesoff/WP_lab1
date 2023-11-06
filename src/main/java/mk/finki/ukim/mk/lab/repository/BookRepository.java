package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    public List<Book> findAll(){
        return DataHolder.bookList;
    };
    public Book findByIsbn(String isbn){
        return DataHolder.bookList.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().orElse(new Book());
    };
    Author addAuthorToBook(Author author, Book book){
        DataHolder.bookList.add(book);
        Book bookAdd = findByIsbn(book.getIsbn());
        bookAdd.getAuthors().add(author);
        return author;
    };
}
