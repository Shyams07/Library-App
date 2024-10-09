package com.example.project.backend.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.project.backend.model.FavoriteBook;

@Repository
public interface FavoriteBookRepository extends MongoRepository<FavoriteBook, String> {
    void deleteByCanonicalIsbnAndEmail(String canonicalIsbn, String email);
    List<FavoriteBook> findByEmail(String email);
}
