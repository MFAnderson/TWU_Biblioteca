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
    }

    public void doSomething() throws IOException {
        String input = reader.readLine();
        if (input.equalsIgnoreCase("Quit")) {
            shouldContinue = false;
            return;
        }
        if (input.equals("1")) {
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
        }
        else {
            printStream.println("Select a valid option!");
        }
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }
}
