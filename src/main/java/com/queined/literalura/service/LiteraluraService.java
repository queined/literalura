package com.queined.literalura.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queined.literalura.dto.BookDTO;
import com.queined.literalura.model.Author;
import com.queined.literalura.model.Book;
import com.queined.literalura.repository.AuthorRepository;
import com.queined.literalura.repository.BookRepository;

import jakarta.transaction.Transactional;

/**
 * This class represents the service layer for the Literalura application.
 * It provides methods for saving books, retrieving books and authors, and getting top books.
 */
@Service
public class LiteraluraService {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Saves a book in the database.
     * If the book already exists, an exception is thrown.
     * If the author of the book already exists, the existing author is used.
     * 
     * @param bookDTO The book data transfer object.
     * @return The saved book.
     * @throws IllegalArgumentException if the book already exists.
     */
    @Transactional
    public Book saveBook(BookDTO bookDTO) {
        Author author = new Author(bookDTO.authors().get(0));

        if(repository.existsByTitle(bookDTO.title())) {
            throw new IllegalArgumentException("Book already exists");
        }

        if(authorRepository.existsByName(author.getName())) {
            author = authorRepository.findByName(author.getName());
        }

        return repository.save(new Book(bookDTO, author));    
    }

    /**
     * Retrieves all books from the database.
     * 
     * @return A list of all books.
     */
    public List<Book> getBooks() {
        return repository.findAll();
    }

    /**
     * Retrieves all authors from the database.
     * 
     * @return A list of all authors.
     */
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Retrieves the top books from the database.
     * 
     * @return A list of top books.
     */
    public List<Book> topBooks() {
        return repository.topBooks();
    }
}
