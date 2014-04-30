package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        PrintStream out = System.out;

        Library library = new Library(initialBooks(), out, new StringJoiner(), new HashSet<String>());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Menu menu = new Menu(out, library, reader);
        BibliotecaController controller = new BibliotecaController(out, library, menu);
        controller.start();
    }
    private static Collection<String> initialBooks() {
        Collection<String> initialBooks = new ArrayList<String>();
        initialBooks.add("The Bible");
        initialBooks.add("Slaughterhouse Five");

        return initialBooks;
    }

}
