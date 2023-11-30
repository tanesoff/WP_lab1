package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.BookStore;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookStoreService {
    public List<BookStore> findAll();

    public List<Long> getStoreIds();
}
