package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.BookStore;
import mk.finki.ukim.mk.lab.model.DTO.KinzarnicaNameId;
import mk.finki.ukim.mk.lab.repository.BookStoreRepository;
import mk.finki.ukim.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public List<Long> getStoreIds() {
        return bookStoreRepository.findAll().stream().map(BookStore::getId).collect(Collectors.toList());
    }

    public List<KinzarnicaNameId> kinzarnicaNameId(){
        return bookStoreRepository.findAll().stream().map(bookStore -> new KinzarnicaNameId(bookStore.getId(), bookStore.getName())).collect(Collectors.toList());
    }

    public Long getUniqueId(){
        Random random = new Random();


        Optional<Long> firstNumber = bookStoreRepository.findAll().stream().map(bookStore -> {
            Long number = random.nextLong();
            do {

            } while (bookStore.getId() != number);

            return number;
        }).findFirst();

        return firstNumber.get();
    }

    public BookStore getBookStoreById(Long id){
        return bookStoreRepository.findAll().stream().filter(bookStore -> bookStore.getId().equals(id)).findFirst().get();
    }

}
