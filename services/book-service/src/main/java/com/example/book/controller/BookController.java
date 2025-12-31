package com.example.book.controller;

import com.example.book.model.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookRepository repository;

  @PostMapping
  public Book addBook(@RequestBody Book book) {
    return repository.save(book);
  }
}
