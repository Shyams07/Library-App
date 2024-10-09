package com.example.Books;

import com.example.Books.controller.BookController;
import com.example.Books.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BooksApplicationTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private BookService bookService;

    private BookController bookController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        bookController = new BookController(bookService);
    }

    @Test
    void contextLoads() {
        // Test to ensure that the application context loads successfully and that the services are not null.
        assertNotNull(bookService);
        assertNotNull(bookController);
    }

    @Test
    void testSearchBooksService() {
        // Test to verify that the BookService's searchBooks method returns the expected mocked response.
        BookService localBookService = new BookService(restTemplate);
        
        when(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Object.class)
        )).thenReturn(new ResponseEntity<>("Mocked response", HttpStatus.OK));

        Object result = localBookService.searchBooks("Test Title", "Test Author", "Fiction", "Novel", "Test Series");
        
        assertNotNull(result);
        assertEquals("Mocked response", result);
    }

    @Test
    void testSearchBooksController() {
        // Test to verify that the BookController's searchBooks method returns the expected mocked response.
        when(bookService.searchBooks(anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn("Mocked controller response");

        ResponseEntity<?> response = bookController.searchBooks("Test Title", "Test Author", "Fiction", "Novel", "Test Series");
        
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mocked controller response", response.getBody());
    }
    
    @Test
    void testSearchBooksControllerUnexpectedResponse() {
    	// This test demonstrates an assertion mismatch with the expected response
        when(bookService.searchBooks(anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn("This is not the expected response");

        ResponseEntity<?> response = bookController.searchBooks("Some Title", "Some Author", "Fiction", "Novel", "Some Series");      
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expected response", response.getBody());
    }

    @Test
    void testSearchBooksControllerWithDefaultValues() {
        // Test to verify that the BookController's searchBooks method handles default values correctly.
        when(bookService.searchBooks(isNull(), isNull(), isNull(), eq("Fiction"), eq("Wings of Fire")))
            .thenReturn("Mocked default response");

        ResponseEntity<?> response = bookController.searchBooks(null, null, null, "Fiction", "Wings of Fire");
        
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Mocked default response", response.getBody());
    }
    
}
