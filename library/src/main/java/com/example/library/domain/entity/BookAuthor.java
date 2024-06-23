package com.example.library.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "book_authors")
public class BookAuthor {
    @EmbeddedId
    BookAuthorId id;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    public BookAuthor() {
    }

    public BookAuthor(BookAuthorId id, Author author, Book book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }

    public BookAuthorId getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Book getBook() {
        return book;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setId(BookAuthorId id) {
        this.id = id;
    }
}
