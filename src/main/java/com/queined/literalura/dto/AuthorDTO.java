package com.queined.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Data Transfer Object (DTO) for an author.
 * An AuthorDTO object contains information about an author, such as their name, birth year, and death year.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDTO(
        String name,
        @JsonProperty("birth_year") Short birthYear,
        @JsonProperty("death_year") Short deathYear) {
}
