package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;


public class ReturnBookCommand implements Command{
    private final PrintStream printStream;
    private final BufferedReader reader;
    private final Library library;

    public ReturnBookCommand( PrintStream printStream, BufferedReader reader, Library library) {
        this.printStream = printStream;
        this.reader = reader;
        this.library = library;
    }

    @Override
    public void execute() throws IOException {
        printStream.println("Which book would you like to return?");
        String book = reader.readLine();
        if (library.returnBook(book)){
            printStream.println("Thank you for returning the book.");
        } else {
            printStream.println("That is not a valid book to return.");
        }
    }

    @Override
    public String commandName() {
        return "Return book";
    }
}
