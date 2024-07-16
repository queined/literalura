package com.queined.literalura.model;

/**
 * Exception thrown when an illegal language is encountered.
 */
public class IllegalLanguageException extends RuntimeException {
    /**
     * Constructs a new IllegalLanguageException with the specified detail message.
     *
     * @param message the detail message
     */
    public IllegalLanguageException(String message) {
        super(message);
    }
}
