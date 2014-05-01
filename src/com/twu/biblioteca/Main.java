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
        Menu menu = new Menu(out, library, reader, commandMap);
        populateCommandMap(commandMap, menu);
        BibliotecaController controller = new BibliotecaController(out, library, menu);
        controller.start();
    }

    private static void populateCommandMap(Map<String, Command> commandMap, Menu menu) {
        commandMap.put("1", new ListBooksCommand(menu));
        commandMap.put("2", new CheckOutCommand(menu));
        commandMap.put("3", new ReturnBookCommand(menu));
    }

    private static Collection<String> initialBooks() {
        Collection<String> initialBooks = new ArrayList<String>();
        initialBooks.add("The Bible");
        initialBooks.add("Slaughterhouse Five");

        return initialBooks;
    }

}
