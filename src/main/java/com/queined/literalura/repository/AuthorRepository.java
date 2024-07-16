package com.queined.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.queined.literalura.model.Author;

/**
 * The AuthorRepository interface is responsible for providing CRUD operations
 * for the Author entity.
 */
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    /**
     * Checks if an author with the given name exists in the repository.
     * 
     * @param name The name of the author to check.
     * @return true if an author with the given name exists, false otherwise.
     */
    public boolean existsByName(String name);

    /**
     * Finds an author by their name.
     * 
     * @param name The name of the author to find.
     * @return The author with the given name, or null if not found.
     */
    public Author findByName(String name);
}
