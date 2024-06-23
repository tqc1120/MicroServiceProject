package com.example.library.service.impl;

import com.example.library.domain.dto.AuthorDto;
import com.example.library.domain.entity.Author;
import com.example.library.domain.entity.Book;
import com.example.library.domain.entity.BookAuthor;
import com.example.library.domain.entity.BookAuthorId;
import com.example.library.exception.DuplicateResourceException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookAuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManageServiceImpl implements ManageService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public ManageServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookAuthorRepository bookAuthorRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        if (authorRepository.getAuthorsByName(authorDto.getName()).size() > 0) {
            throw new DuplicateResourceException("Author with name " + authorDto.getName() + " already exists");
        }

        Author author = new Author();

        author.setName(authorDto.getName());
        author = authorRepository.save(author);

        if (authorDto.getBooks() != null && !authorDto.getBooks().isEmpty()) {
            Set<BookAuthor> bookAuthors = new HashSet<>();
            for (String bookTitle : authorDto.getBooks()) {
                List<Book> books = bookRepository.findByTitle(bookTitle);
                Book book;
                if (!books.isEmpty()) {
                    book = books.get(0); // Use the first book found
                } else {
                    book = new Book();
                    book.setTitle(bookTitle);
                    book = bookRepository.save(book);
                }

                BookAuthorId bookAuthorId = new BookAuthorId(author.getAuthor_id(), book.getBook_id());
                if (!bookAuthors.stream().anyMatch(ba -> ba.getId().equals(bookAuthorId))) {
                    BookAuthor bookAuthor = new BookAuthor(bookAuthorId, author, book);
                    bookAuthors.add(bookAuthor);
                }
            }
            bookAuthorRepository.saveAll(bookAuthors);
            author.setBookAuthors(bookAuthors);
        }
        return mapToAuthorDto(author);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.deleteById(book.getBook_id());

        Set<BookAuthor> bookAuthors = book.getBookAuthors();
        bookAuthorRepository.deleteAll(bookAuthors);
    }

    @Override
    public AuthorDto updateAuthorName(Long id, String newName) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        author.setName(newName);
        authorRepository.save(author);
        return mapToAuthorDto(author);
    }

    private AuthorDto mapToAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthor_id());
        dto.setName(author.getName());
        dto.setBooks(author.getBookAuthors().stream().map(bookAuthor -> bookAuthor.getBook().getTitle()).collect(Collectors.toSet()));
        return dto;
    }
}
