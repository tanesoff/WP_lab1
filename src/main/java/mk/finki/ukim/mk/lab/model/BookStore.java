package mk.finki.ukim.mk.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class BookStore {
    private Long id;
    private String name;
    private String city;
    private String address;
    private List<Book> bookList;

    public BookStore(String name, String city, String address, List<Book> bookList) {
        this.id = (long) (Math.random() * 1000);
        this.name = name;
        this.city = city;
        this.address = address;
        this.bookList = bookList;
    }
}
