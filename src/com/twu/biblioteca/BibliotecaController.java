package com.twu.biblioteca;

import java.io.IOException;
import java.io.PrintStream;

public class BibliotecaController {


    private final PrintStream printStream;
    private final Menu menu;

    public BibliotecaController(PrintStream printStream, Menu menu) {
        this.printStream = printStream;
        this.menu = menu;
    }

    public void start() throws IOException {
        printStream.println("Welcome!");
        do {
            menu.printOptions();
            menu.selectOption();
        } while(menu.shouldContinue());
    }
}
