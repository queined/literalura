package com.queined.literalura.model;

import java.util.List;

import com.queined.literalura.service.Representable;

import lombok.Getter;

/**
 * Represents a language with its corresponding code.
 */
@Getter
public enum Language implements Representable {
    ENGLISH("Inglés", "en"),
    SPANISH("Español", "es"),
    FRENCH("Frances", "fr"),
    GERMAN("Alemán", "de"),
    PORTUGUESE("Portugués", "pt"),;

    private String language;
    private String code;
    public static List<Representable> languages = List.of(Language.values());

    /**
     * Constructs a new Language enum with the specified language and code.
     *
     * @param language the language name
     * @param code     the language code
     */
    private Language(String language, String code) {
        this.language = language;
        this.code = code;
    }

    /**
     * Returns the Language enum constant that corresponds to the given code.
     *
     * @param code the language code
     * @return the Language enum constant
     * @throws IllegalLanguageException if the code does not match any Language enum constant
     */
    public static Language fromCode(String code) {
        for (Language language : Language.values()) {
            if (language.code.equals(code)) {
                return language;
            }
        }
        throw new IllegalLanguageException("Invalid language code");
    }

    @Override
    public String representation() {
        return this.language;
    }
}
