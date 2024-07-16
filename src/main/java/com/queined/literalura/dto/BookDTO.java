package com.queined.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a Data Transfer Object (DTO) for a book.
 * This class contains information about the book, such as its title, authors, languages, and download count.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        String title,
        List<AuthorDTO> authors,
        List<String> languages,
        @JsonProperty("download_count") Long downloadCount) {
}
