package com.queined.literalura.main;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.queined.literalura.dto.ApiResponseDTO;
import com.queined.literalura.dto.BookDTO;
import com.queined.literalura.model.Book;
import com.queined.literalura.model.IllegalLanguageException;
import com.queined.literalura.model.Language;
import com.queined.literalura.model.Option;
import com.queined.literalura.service.LiteraluraService;
import com.queined.literalura.service.Deserializer;
import com.queined.literalura.service.Display;
import com.queined.literalura.service.Representable;
import com.queined.literalura.service.RequestHandler;

public class Main {

    private LiteraluraService service;
    private Display display;
    private Scanner scanner = new Scanner(System.in);

    public Main(LiteraluraService service) {
        this.service = service;
        this.display = new Display("opción", scanner, Option.options);
    }

    public void main() {
        Option option;
        display.showMessage("Bienvenido a Literalura!");

        while (display.isRunning()) {
            try {
                option = (Option) display.selectItem("Seleccione una opción: ");
                this.performAction(option);

            } catch (IndexOutOfBoundsException e) {
                display.showMessage("El usuario solicitó salir del programa.");
            }
        }
    }

    private void performAction(Option option) {
        switch (option.getKey()) {
            case 1:
                this.addBook();
                break;

            case 2:
                this.showBooks();
                break;

            case 3:
                this.showAuthors();
                break;

            case 4:
                this.showAliveAuthors();
                break;

            case 5:
                this.showBooksByLanguage();
                break;

            case 6:
                this.showTop10Books();
                break;

            case 7:
                this.showStatistics();
                break;

            default:
                break;
        }
    }

    private void addBook() {
        RequestHandler requestHandler = new RequestHandler();
        String url = "https://gutendex.com/books/?search=";
        String title;
        String response;
        ApiResponseDTO result;
        BookDTO book;

        display.print("Ingrese el título del libro que desea agregar: ");
        title = scanner.nextLine().replace(" ", "%20");
        response = requestHandler.get(url + title);
        result = Deserializer.deserialize(response, ApiResponseDTO.class);

        if (result.count() > 0) {
            book = result.results().get(0);
            try {
                service.saveBook(book);
                display.showMessage("El libro ha sido agregado exitosamente.");
            } catch (IllegalArgumentException e) {
                display.showMessage("El libro ya se encuentra registrado.");
            } catch (IllegalLanguageException e) {
                display.showMessage("No se encontró el idioma del libro en la lista de idiomas permitidos.");
            }
        } else {
            display.showMessage("No se encontraron resultados para la búsqueda realizada.");
        }
    }

    private void showBooks() {
        List<Representable> books = service.getBooks().stream()
                .map(book -> (Representable) book)
                .toList();
        Display display = new Display("libro", books);
        display.showItems("Libros registrados:");
    }

    private void showAuthors() {
        List<Representable> authors = service.getAuthors().stream()
                .map(author -> (Representable) author)
                .toList();
        Display display = new Display("autor", authors);
        display.showItems("Autores registrados:");
    }

    private void showAliveAuthors() {
        Short givenYear;
        try {
            display.print("Ingrese el año: ");
            givenYear = Short.valueOf(scanner.nextLine());
        } catch (NumberFormatException e) {
            display.showMessage("El año ingresado no es válido.");
            return;
        }

        List<Representable> authors = service.getAuthors().stream()
                .filter(author -> author.isAlive(givenYear))
                .map(author -> (Representable) author)
                .toList();
        Display display = new Display("autor", authors);
        display.showItems("Autores vivos en el año " + givenYear + ":");
    }

    private void showBooksByLanguage() {
        Display display = new Display("idioma", Language.languages);
        Language language = (Language) display.selectItem("Seleccione un idioma: ");
        List<Representable> books = service.getBooks().stream()
                .filter(book -> book.getLanguage().equals(language))
                .map(book -> (Representable) book)
                .toList();
        display = new Display("libro", books);
        display.showItems("Libros en " + language.representation() + ":");
    }

    private void showTop10Books() {
        List<Representable> books = service.topBooks().stream()
                .map(book -> (Representable) book)
                .toList();
        Display display = new Display("libro", books);
        display.showItems("Top 10 libros más descargados:");
    }

    private void showStatistics() {
        DoubleSummaryStatistics est = service.getBooks().stream()
                .collect(Collectors.summarizingDouble(Book::getDownloadCount));


        String statistics = "Estadísticas:\n" +
                "- Cantidad de libros: " + est.getCount() + "\n" +
                "- Descargas totales: " + (int) est.getSum() + "\n" +
                "- Promedio de descargas: " + String.format("%.2f", est.getAverage()) + "\n" +
                "- Máximo de descargas: " + (int) est.getMax() + "\n" +
                "- Mínimo de descargas: " + (int) est.getMin();

        display.showMessage(statistics);
    }

}
