package com.queined.literalura.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents the response data transfer object (DTO) for an API response.
 * This class is used to deserialize the JSON response from the API into Java objects.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ApiResponseDTO(
        Long count,
        List<BookDTO> results) {
}
