package com.example.author.controller;

import com.example.author.model.Author;
import com.example.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

  @Autowired
  private AuthorRepository repository;

  @PostMapping
  public Author addAuthor(@RequestBody Author author) {
    return repository.save(author);
  }
}
