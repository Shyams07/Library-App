package com.example.Books.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookService {

    private final RestTemplate restTemplate;
    private final String API_URL = "https://book-finder1.p.rapidapi.com/api/search";

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object searchBooks(String title, String author, String categories, String bookType, String series) {
        // Create request parameters
        StringBuilder url = new StringBuilder(API_URL)
            .append("?book_type=").append(bookType)
            .append("&series=").append(series);

        if (title != null) url.append("&title=").append(title);
        if (author != null) url.append("&author=").append(author);
        if (categories != null) url.append("&categories=").append(categories);

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-key", "5339afb542msh96d872c44663e5dp1dbf17jsn24e51d229298");
        headers.add("x-rapidapi-host", "book-finder1.p.rapidapi.com");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Call the external API
        return restTemplate.exchange(url.toString(), HttpMethod.GET, entity, Object.class).getBody();
    }
}