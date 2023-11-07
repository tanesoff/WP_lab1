package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.Author;
import mk.finki.ukim.mk.lab.model.Book;
import mk.finki.ukim.mk.lab.repository.AuthorRepository;
import mk.finki.ukim.mk.lab.repository.BookRepository;
import mk.finki.ukim.mk.lab.service.AuthorService;
import mk.finki.ukim.mk.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements AuthorService, BookService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        if (authorRepository.findById(id).isPresent())
            return authorRepository.findById(id).get();
        return null;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Book byIsbnBook = bookRepository.findByIsbn(isbn);
        Optional<Author> byAuthorId = authorRepository.findById(authorId);
        if(byAuthorId.isPresent()){
            byIsbnBook.getAuthors().add(byAuthorId.get());
            return byAuthorId.get();
        }
        return null;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public List<Author> getAllAuthorsThatAreNotInTheBook(String isbn){
        return authorRepository.findAll().stream().filter(author -> !bookRepository.findByIsbn(isbn).getAuthors().contains(author)).collect(Collectors.toList());
    }

    public List<Book> getAllBooksByAuthor(Long id){
        return bookRepository.findAll().stream().filter(book -> book.getAuthors().contains(authorRepository.findById(id).get())).collect(Collectors.toList());
    }
}
