package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * Created by manderso on 4/29/14.
 */
public class Menu {
    private PrintStream printStream;
    private Library library;
    private BufferedReader reader;
    private Map<String, Command> commandMap;
    private boolean shouldContinue = true;

    public Menu(PrintStream printStream, Library library, BufferedReader reader, Map<String, Command> commandMap) {
        this.printStream = printStream;
        this.library = library;
        this.reader = reader;
        this.commandMap = commandMap;
    }

    public void printOptions() {
        for(String input : commandMap.keySet()) {
            printStream.println(input + ") " + commandMap.get(input).commandName());
        }
    }

    public void doSomethingWithOptions() throws IOException {
        String input = reader.readLine();
        if (input.equalsIgnoreCase("Quit")) {
            shouldContinue = false;
        } else if (commandMap.containsKey(input)){
            commandMap.get(input).execute();
        } else {
            printStream.println("Select a valid option!");
        }
    }

    public void returnBook() throws IOException {
        printStream.println("Which book would you like to return?");
        String book = reader.readLine();
        if (library.returnBook(book)){
            printStream.println("Thank you for returning the book.");
        } else {
            printStream.println("That is not a valid book to return.");
        }
    }

    public void listBooks() {
        library.listBooks();
    }

    public void checkoutBook() throws IOException {
        printStream.println("Which book would you like to check out?");
        String book = reader.readLine();
        boolean success = library.checkout(book);
        if (success) {
            printStream.println("Thank you! Enjoy the book");
        } else {
            printStream.println("That book is not available.");
        }
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }
}
