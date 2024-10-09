package com.example.project.backend;

import com.example.project.backend.model.FavoriteBook;
import com.example.project.backend.service.FavoriteBookService;
import com.example.project.backend.controller.FavoriteBookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ApplicationTests {

    @InjectMocks
    private FavoriteBookController favoriteBookController;

    @Mock
    private FavoriteBookService favoriteBookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFavoriteBooks() {
        // Test case to verify retrieval of favorite books for a user
        String userEmail = "test@example.com";
        List<FavoriteBook> favoriteBooks = new ArrayList<>();
        favoriteBooks.add(new FavoriteBook("123456789", "Book Title", "Book Author", 300, "http://coverurl.com", userEmail));
        when(favoriteBookService.getAllFavoriteBooks(userEmail)).thenReturn(favoriteBooks);
        ResponseEntity<List<FavoriteBook>> response = favoriteBookController.getFavoriteBooks(userEmail);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals("Book Title", response.getBody().get(0).getTitle());
    }

    @Test
    void testSaveFavoriteBook() {
        // Test case to verify saving a favorite book
        FavoriteBook favoriteBook = new FavoriteBook("123456789", "Book Title", "Book Author", 300, "http://coverurl.com", "test@example.com");      
        when(favoriteBookService.addFavoriteBook(any(FavoriteBook.class))).thenReturn(favoriteBook);
        ResponseEntity<FavoriteBook> response = favoriteBookController.saveFavoriteBook(favoriteBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book Title", response.getBody().getTitle());
        assertEquals("test@example.com", response.getBody().getEmail());
    }

    @Test
    void testDeleteFavoriteBook() {
        // Test case to verify deletion of a favorite book
        String canonicalIsbn = "123456789";
        String userEmail = "test@example.com";
        ResponseEntity<Void> response = favoriteBookController.deleteFavoriteBook(canonicalIsbn, userEmail);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(favoriteBookService, times(1)).removeFavoriteBook(canonicalIsbn, userEmail);
    }

    @Test
    void testSaveFavoriteBook_WithNullValues() {
        // Test case to verify saving a favorite book with null values
        FavoriteBook favoriteBook = new FavoriteBook(null, null, null, 0, null, null);
        ResponseEntity<FavoriteBook> response = favoriteBookController.saveFavoriteBook(favoriteBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(favoriteBookService, times(1)).addFavoriteBook(any(FavoriteBook.class));
    }
 
    @Test
    void testSaveFavoriteBook_ShouldFail() {
        // Test case to verify saving a favorite book fails when a different book is returned
        FavoriteBook favoriteBook = new FavoriteBook("123456789", "Book Title", "Book Author", 300, "http://coverurl.com", "test@example.com");
        FavoriteBook differentBook = new FavoriteBook("987654321", "Different Title", "Different Author", 200, "http://differenturl.com", "test@example.com");
        when(favoriteBookService.addFavoriteBook(any(FavoriteBook.class))).thenReturn(differentBook);
        ResponseEntity<FavoriteBook> response = favoriteBookController.saveFavoriteBook(favoriteBook);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book Title", response.getBody().getTitle());
    }
}
