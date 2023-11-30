package mk.finki.ukim.mk.lab.bootstrap;


import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Author> authorList;
    public static List<Book> bookList;
    public static List<BookStore> bookStores;


    @PostConstruct
    public void init(){
        authorList = new ArrayList<>();
        bookList = new ArrayList<>();
        bookStores = new ArrayList<>();

        BookStore prosetnoBookStore = new BookStore("Prosvetno Delo", "Skopje", "8-mi mart", bookList);
        bookStores.add(prosetnoBookStore);
        BookStore snezana = new BookStore("Snezana", "Ohrid", "Slavej Planina", new ArrayList<Book>());
        bookStores.add(snezana);
        bookStores.add(new BookStore("Sakam Knigi", "Skopje", "Partizanska", new ArrayList<Book>()));

        Author Cal = new Author(1L, "Cal", "Newport", "Calvin C. Newport is an American nonfiction author and associate professor of computer science at Georgetown University.");
        authorList.add(Cal);
        Author Niel = new Author(2L, "Neil ", "deGrasse Tyson", "Neil deGrasse Tyson is an American astrophysicist, author, and science communicator. ");
        authorList.add(Niel);
        Author Orwell = new Author(3L, "George", "Orwell", "Eric Arthur Blair, better known by his pen name George Orwell, was an English novelist, essayist, journalist, and critic. His work is characterised by lucid prose, social criticism, opposition to totalitarianism, and support of democratic socialism");
        authorList.add(Orwell);
        Author Jack = new Author(4L, "Jack", "London", "John Griffith Chaney, better known as Jack London, was an American novelist, journalist and activist. A pioneer of commercial fiction and American magazines, he was one of the first American authors to become an international celebrity and earn a large fortune from writing");
        authorList.add(Jack);
        Author Nisio = new Author(5L, "Nisio", "Isin", "Nisio Isin, stylized as NISIOISIN to emphasize the palindrome, is a pseudonymous Japanese novelist, manga author, and screenplay writer");
        authorList.add(Nisio);

        Book NisemonogatariBook = new Book("9781942993988", "Nisemonogatari", "Light Novel", 2008);
        NisemonogatariBook.getAuthors().add(Nisio);
        NisemonogatariBook.setBookStore(prosetnoBookStore);
        bookList.add(NisemonogatariBook);

        Book ToBuildaFireBook = new Book("9782897176792", "To Build a Fire", "Horror", 1902);
        ToBuildaFireBook.getAuthors().add(Jack);
        ToBuildaFireBook.setBookStore(prosetnoBookStore);
        bookList.add(ToBuildaFireBook);

        Book AnimalFarmBook = new Book("9784942993988", "Animal Farm", "Political satire", 1945);
        AnimalFarmBook.getAuthors().add(Orwell);
        AnimalFarmBook.setBookStore(snezana);
        bookList.add(AnimalFarmBook);

        Book AstrophysicsBook = new Book("9788429776317", "Astrophysics for People in a Hurry", "Non-fiction", 2017);
        AstrophysicsBook.getAuthors().add(Niel);
        AstrophysicsBook.setBookStore(prosetnoBookStore);
        bookList.add(AstrophysicsBook);

        Book DeepWorkBook = new Book("9781455586660", "Deep Work", "Self-help", 2016);
        DeepWorkBook.getAuthors().add(Cal);
        DeepWorkBook.setBookStore(snezana);
        bookList.add(DeepWorkBook);

    }
}
