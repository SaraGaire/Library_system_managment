package com.example.library.controller;


import com.example.library.model.Book;
import com.example.library.repo.BookRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*")
public class BooksController {
private final BookRepository books;
public BooksController(BookRepository b) { this.books = b; }


@GetMapping
public List<Book> all(@RequestParam Optional<String> q) {
return q.filter(s -> !s.isBlank())
.map(books::findByTitleContainingIgnoreCase)
.orElseGet(books::findAll);
}


@PostMapping
public Book create(@RequestBody Book b) { return books.save(b); }
}
