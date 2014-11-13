package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CheckOutCommand implements Command {
    private final PrintStream printStream;
    private final BufferedReader reader;
    private final Library library;

    public CheckOutCommand(PrintStream printStream, BufferedReader reader, Library library) {
        this.printStream = printStream;
        this.reader = reader;
        this.library = library;
    }

    @Override
    public void execute() throws IOException {

        printStream.println("Which book would you like to check out?");
        String book = reader.readLine();
        boolean success = library.checkout(book);
        if (success) {
            printStream.println("Thank you! Enjoy the book");
        } else {
            printStream.println("That book is not available.");
        }
    }

    @Override
    public String commandName() {
        return "Check out book";
    }
}
