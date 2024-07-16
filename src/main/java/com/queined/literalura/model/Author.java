package com.queined.literalura.model;

import com.queined.literalura.dto.AuthorDTO;
import com.queined.literalura.service.Representable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents an author in the literature application.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author implements Representable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Short birthYear;
    private Short deathYear;

    /**
     * Constructs an Author object with the given authorDTO.
     *
     * @param authorDTO the authorDTO object containing the author's information
     */
    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.name();
        this.birthYear = authorDTO.birthYear();
        this.deathYear = authorDTO.deathYear();
    }

    /**
     * Returns a string representation of the Author object.
     *
     * @return a string representation of the Author object
     */
    @Override
    public String toString() {
        return this.name + " (" + this.birthYear + "-" + this.deathYear + ")";
    }

    /**
     * Returns the representation of the Author object.
     *
     * @return the representation of the Author object
     */
    @Override
    public String representation() {
        return this.toString();
    }

    /**
     * Checks if the author is alive in the given year.
     *
     * @param givenYear the year to check
     * @return true if the author is alive in the given year, false otherwise
     */
    public Boolean isAlive(Short givenYear) {
        return this.birthYear <= givenYear && (this.deathYear == null || this.deathYear >= givenYear);
    }
}
