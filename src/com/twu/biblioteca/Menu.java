package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by manderso on 4/29/14.
 */
public class Menu {
    private PrintStream printStream;
    private Library library;
    private BufferedReader reader;
    private boolean shouldContinue = true;

    public Menu(PrintStream printStream, Library library, BufferedReader reader) {
        this.printStream = printStream;
        this.library = library;
        this.reader = reader;
    }

    public void printOptions() {
        printStream.println("1) List Books");
        printStream.println("2) Check out book");
        printStream.println("3) Return book");
    }

    public void doSomethingWithOptions() throws IOException {
        String input = reader.readLine();
        if (input.equalsIgnoreCase("Quit")) {
            shouldContinue = false;
        } else if (input.equals("1")) {
            library.listBooks();
        } else if (input.equals("2")) {
            printStream.println("Which book would you like to check out?");
            String book = reader.readLine();
            boolean success = library.checkout(book);
            if (success) {
                printStream.println("Thank you! Enjoy the book");
            } else {
                printStream.println("That book is not available.");
            }
        } else if (input.equals("3")) {
            printStream.println("Which book would you like to return?");
            String book = reader.readLine();
            library.returnBook(book);
            printStream.println("Thank you for returning the book.");
        }
        else {
            printStream.println("Select a valid option!");
        }
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }
}
