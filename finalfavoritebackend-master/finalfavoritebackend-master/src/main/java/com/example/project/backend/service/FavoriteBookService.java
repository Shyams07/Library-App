package com.example.project.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.backend.model.FavoriteBook;
import com.example.project.backend.repository.FavoriteBookRepository;

import java.util.List;

@Service
public class FavoriteBookService {

    @Autowired
    private FavoriteBookRepository favoriteBookRepository;

    public List<FavoriteBook> getAllFavoriteBooks(String email) {
        return favoriteBookRepository.findByEmail(email);
    }

    public FavoriteBook addFavoriteBook(FavoriteBook book) {
        return favoriteBookRepository.save(book);
    }

    public void removeFavoriteBook(String canonicalIsbn, String email) {
        favoriteBookRepository.deleteByCanonicalIsbnAndEmail(canonicalIsbn, email);
    }
}
