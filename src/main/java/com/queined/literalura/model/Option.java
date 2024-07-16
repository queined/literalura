package com.queined.literalura.model;

import java.util.List;

import com.queined.literalura.service.Representable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents an option in a menu or selection.
 * Implements the {@link Representable} interface.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Option implements Representable {
    private Integer key;
    private String value;

    /**
     * A list of predefined options.
     */
    public static List<Representable> options = List.of(
            new Option(1, "Agregar libro por título"),
            new Option(2, "Listar libros registrados"),
            new Option(3, "Listar autores registrados"),
            new Option(4, "Listar autores vivos en un determinado año"),
            new Option(5, "Listar libros por idioma"),
            new Option(6, "Listar top 10 libros más descargados"),
            new Option(7, "Estadísticas generales"));

    /**
     * Returns the string representation of the option.
     *
     * @return The string representation of the option.
     */
    @Override
    public String representation() {
        return this.value;
    }

    /**
     * Returns the string representation of the option.
     *
     * @return The string representation of the option.
     */
    @Override
    public String toString() {
        return this.representation();
    }
}
