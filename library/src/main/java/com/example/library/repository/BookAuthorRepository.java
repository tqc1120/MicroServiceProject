package com.example.library.repository;

import com.example.library.domain.entity.BookAuthor;
import com.example.library.domain.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}
