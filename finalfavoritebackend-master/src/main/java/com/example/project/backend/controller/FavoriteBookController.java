package com.example.project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.backend.model.FavoriteBook;
import com.example.project.backend.service.FavoriteBookService;

import java.util.List;

@RestController
@RequestMapping("favorites")
public class FavoriteBookController {

    @Autowired
    private FavoriteBookService favoriteBookService;

    // Get all favorite books for a specific user
    @GetMapping("/{userEmail}")
    public ResponseEntity<List<FavoriteBook>> getFavoriteBooks(@PathVariable String userEmail) {
        List<FavoriteBook> favoriteBooks = favoriteBookService.getAllFavoriteBooks(userEmail);
        return new ResponseEntity<>(favoriteBooks, HttpStatus.OK);
    }

    // Save a favorite book for a specific user
    @PostMapping
    public ResponseEntity<FavoriteBook> saveFavoriteBook(@RequestBody FavoriteBook favoriteBook) {
        FavoriteBook savedFavoriteBook = favoriteBookService.addFavoriteBook(favoriteBook);
        return new ResponseEntity<>(savedFavoriteBook, HttpStatus.OK);
    }

    // Delete a favorite book by its canonical ISBN and user email
    @DeleteMapping("/{canonicalIsbn}/{userEmail}")
    public ResponseEntity<Void> deleteFavoriteBook(@PathVariable String canonicalIsbn, @PathVariable String userEmail) {
        favoriteBookService.removeFavoriteBook(canonicalIsbn, userEmail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
