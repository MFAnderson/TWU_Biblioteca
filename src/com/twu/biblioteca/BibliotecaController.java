package com.twu.biblioteca;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by manderso on 4/29/14.
 */
public class BibliotecaController {


    private PrintStream printStream;
    private Library library;
    private Menu menu;

    public BibliotecaController(PrintStream printStream, Library library, Menu menu) {
        this.printStream = printStream;
        this.library = library;
        this.menu = menu;
    }

    public void start() throws IOException {
        printStream.println("Welcome!");
        do {
            menu.printOptions();
            menu.doSomething();
        } while(menu.shouldContinue());
    }
}
