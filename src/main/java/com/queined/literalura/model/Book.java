package com.queined.literalura.model;

import com.queined.literalura.dto.BookDTO;
import com.queined.literalura.service.Representable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a book entity.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book implements Representable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;
    @Enumerated(EnumType.STRING)
    private Language language;
    private Long downloadCount;

    /**
     * Constructs a Book object from a BookDTO and an Author.
     *
     * @param bookDTO The BookDTO object containing the book details.
     * @param author The Author object associated with the book.
     */
    public Book(BookDTO bookDTO, Author author) {
        this.title = bookDTO.title();
        this.author = author;
        this.language = Language.fromCode(bookDTO.languages().get(0));
        this.downloadCount = bookDTO.downloadCount();
    }

    /**
     * Returns a string representation of the book.
     *
     * @return The string representation of the book.
     */
    @Override
    public String toString() {
        return title + ", by " + author;
    }

    /**
     * Returns the representation of the book.
     *
     * @return The representation of the book.
     */
    @Override
    public String representation() {
        return this.toString();
    }
}
