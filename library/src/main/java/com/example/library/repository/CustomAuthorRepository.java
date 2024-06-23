package com.example.library.repository;

import com.example.library.domain.entity.Author;

import java.util.List;

public interface CustomAuthorRepository {
    List<Author> getAuthorsByName(String name);
}
