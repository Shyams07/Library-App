package com.example.project.backend.model;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class FavoriteBook {
   
    private String canonicalIsbn;  // Unique identifier for the book

    private String title;
    private String author;
    private int pageCount;
    private String coverArtUrl;
    private String email;
}
