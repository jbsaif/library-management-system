package com.library.system.repository;

import com.library.system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    // NEW METHOD for category filtering
    List<Book> findByCategoryIgnoreCase(String category);
}