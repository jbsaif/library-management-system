package com.library.system.service;

import com.library.system.model.Book;
import java.util.List;

public interface BookService {

    Book save(Book book);

    Book findById(Long id);

    List<Book> findAll();

    List<Book> searchByTitle(String title);

    // NEW METHOD for category filtering
    List<Book> findByCategory(String category);

    Book update(Book book);

    void deleteById(Long id);
}