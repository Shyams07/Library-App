package com.example.Books.controller;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Books.service.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    @Cacheable("books") // Caching the response
    public ResponseEntity<?> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String categories,
            @RequestParam(defaultValue = "Fiction") String bookType,
            @RequestParam(defaultValue = "Wings of Fire") String series
    ) {
        return ResponseEntity.ok(bookService.searchBooks(title, author, categories, bookType, series));
    }
}