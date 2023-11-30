package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Book {
    private Long id;
    String isbn;
    String title;
    String genre;
    int year;
    List<Author> authors;
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year) {
        this.id = (long)(Math.random() * 1000);
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.authors = new ArrayList<>();
        bookStore = new BookStore();
    }
}
