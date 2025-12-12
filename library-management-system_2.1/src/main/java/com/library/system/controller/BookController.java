package com.library.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.library.system.service.BookService;
import com.library.system.service.BorrowService;
import com.library.system.model.Book;

import java.security.Principal;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BorrowService borrowService;

    public BookController(BookService bookService, BorrowService borrowService) {
        this.bookService = bookService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("title", "All Books"); // ADDED: Passing title to the fragment
        return "books";
    }

    // Filter books by category
    @GetMapping("/category/{categoryName}")
    public String listByCategory(@PathVariable("categoryName") String categoryName, Model model) {
        model.addAttribute("books", bookService.findByCategory(categoryName));
        model.addAttribute("title", categoryName + " Books"); // ADDED: Passing title to the fragment
        return "books";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("title", "Add New Book"); // ADDED: Passing title to the fragment
        return "books/add";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("title", "Edit Book"); // ADDED: Passing title to the fragment
        return "books/edit";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        model.addAttribute("books", bookService.searchByTitle(q));
        model.addAttribute("title", "Search Results"); // ADDED: Passing title to the fragment
        return "books";
    }

    // Retain BORROW ENDPOINT
    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable Long id, Principal principal, Model model) {
        String username = principal.getName();
        try {
            borrowService.borrowBook(id, username);
            return "redirect:/books";
        } catch (IllegalStateException e) {
            return "redirect:/books?error=" + e.getMessage();
        } catch (IllegalArgumentException e) {
            return "redirect:/books?error=" + e.getMessage();
        }
    }
}