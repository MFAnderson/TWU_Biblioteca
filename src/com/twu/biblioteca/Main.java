package com.twu.biblioteca;

import com.twu.biblioteca.commands.CheckOutCommand;
import com.twu.biblioteca.commands.ListBooksCommand;
import com.twu.biblioteca.commands.ReturnBookCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        PrintStream out = System.out;

        Library library = new Library(initialBooks(), out, new StringJoiner(), new HashSet<String>());
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Command> commandMap = new TreeMap<String, Command>();
//        commandMap.put("1", new ListBooksCommand(library));
//        commandMap.put("2", new CheckOutCommand(out, reader, library));
//        commandMap.put("3", new ReturnBookCommand(out, reader, library));
        Menu menu = new Menu(out, reader, commandMap);
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
