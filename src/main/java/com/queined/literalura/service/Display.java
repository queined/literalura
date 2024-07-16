package com.queined.literalura.service;

import java.util.List;
import java.util.Scanner;


/**
 * The Display class represents a display interface for interacting with the user.
 * It provides methods for printing messages, displaying lists, and selecting items.
 */
public class Display {
    private Scanner scanner;
    private String reprType;
    private boolean isRunning = true;
    private int displayLength = 150;
    private List<Representable> list;

    /**
     * Constructs a Display object with the specified representation type, scanner, and list of items.
     *
     * @param reprType the representation type of the items
     * @param scanner the scanner object used for user input
     * @param list the list of items to be displayed
     */
    public Display(String reprType, Scanner scanner, List<Representable> list) {
        this.scanner = scanner;
        this.reprType = reprType;
        this.list = list;
    }

    public Display(String reprType, List<Representable> list) {
        this.scanner = new Scanner(System.in);
        this.reprType = reprType;
        this.isRunning = false;
        this.list = list;
    }

    /**
     * Returns the scanner object used for user input.
     *
     * @return the scanner object
     */
    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Checks if the display is running.
     *
     * @return true if the display is running, false otherwise
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Turns off the display.
     */
    private void turnOff() {
        this.isRunning = false;
    }

    /**
     * Prints the specified message.
     *
     * @param message the message to be printed
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Prints the specified message followed by a new line.
     *
     * @param message the message to be printed
     */
    private void println(String message) {
        System.out.println(message);
    }

    /**
     * Clears the console screen.
     */
    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Checks if the specified string is numeric.
     *
     * @param str the string to be checked
     * @return true if the string is numeric, false otherwise
     */
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Waits for the user to press enter.
     */
    public void waitForUser()  {
        this.print("Presiona enter para continuar...");
        this.scanner.nextLine();
        this.clearConsole();
    }

    /**
     * Prints the list of items.
     */
    private void printList() {
        Representable item;

        this.println("-".repeat(displayLength));

        for (int i = 0; i < list.size(); i++) {
            item = list.get(i);
            this.println(String.format("%-4s", i + 1 + ".") + item.representation());
        }

        this.println("-".repeat(displayLength) + "\n");
    }

    /**
     * Displays the specified message to the user.
     *
     * @param message the message to be displayed
     */
    public void showMessage(String message) {
        this.clearConsole();
        this.println("-".repeat(displayLength));
        this.println(message);
        this.println("-".repeat(displayLength));
        this.waitForUser();
    }

    public void showItems(String message) {
        this.clearConsole();
        this.println("-".repeat(displayLength));
        this.println(message);
        this.printList();
        this.waitForUser();
    }

    /**
     * Allows the user to select an item from the list.
     *
     * @param message the message to be displayed before selecting an item
     * @return the selected item
     */
    public Representable selectItem(String message) {
        int index;
        String input;
        Representable item;

        this.println("-".repeat(displayLength));
        this.println(message);
        this.printList();
        this.print("o digita \".\" para salir: ");
        input = scanner.nextLine();

        if (input.equals(".")) {
            this.turnOff();
            throw new IndexOutOfBoundsException();
        }

        if (this.isNumeric(input)) {
            index = Integer.valueOf(input);

            try {
                item = list.get(index - 1);
                message = "Seleccionó la " + this.reprType.toLowerCase() + ": " + item.representation();
                this.showMessage(message);
            } catch (IndexOutOfBoundsException e) {
                message = "La opción " + index + " no existe. Por favor, selecione una " + this.reprType.toLowerCase() + " válida.";
                this.showMessage(message);
                item = this.selectItem(message);
            }

        } else {
            this.showMessage("Entrada inválida. Por favor, seleccione una opción válida.");
            item = this.selectItem(message);
        }

        return item;
    }
}
