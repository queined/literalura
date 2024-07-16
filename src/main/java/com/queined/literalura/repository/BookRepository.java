package com.queined.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inarixdono.literalura.model.Book;

/**
 * The BookRepository interface provides methods to interact with the book data
 * in the database.
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * Checks if a book with the given title exists in the database.
     *
     * @param title the title of the book
     * @return true if a book with the given title exists, false otherwise
     */
    public boolean existsByTitle(String title);

    /**
     * Retrieves the top 10 books based on the download count.
     *
     * @return a list of the top 10 books
     */
    @Query("""
                SELECT b
                FROM Book b
                ORDER BY b.downloadCount DESC
                LIMIT 10
            """)
    public List<Book> topBooks();

}
