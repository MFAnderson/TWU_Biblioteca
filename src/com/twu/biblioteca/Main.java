package com.twu.biblioteca;

import com.twu.biblioteca.commands.CheckOutCommand;
import com.twu.biblioteca.commands.ListBooksCommand;
import com.twu.biblioteca.commands.QuitCommand;
import com.twu.biblioteca.commands.ReturnBookCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) throws IOException {
        PrintStream out = System.out;
        InputStreamReader in = new InputStreamReader(System.in);
        Library library = new Library(initialBooks(), out, new StringJoiner(), new HashSet<String>());
        BufferedReader reader = new BufferedReader(in);
        AtomicBoolean menuState = new AtomicBoolean(true);
        Map<String, Command> commandMap = generateCommandMap(out, library, reader, menuState);
        Menu menu = new Menu(out, reader, commandMap, menuState);
        BibliotecaController controller = new BibliotecaController(out, menu);
        controller.start();
    }

    private static Map<String, Command> generateCommandMap(PrintStream out, Library library, BufferedReader reader, AtomicBoolean menuState) {
        Map<String, Command> commandMap = new TreeMap<String, Command>();
        commandMap.put("1", new ListBooksCommand(library));
        commandMap.put("2", new CheckOutCommand(out, reader, library));
        commandMap.put("3", new ReturnBookCommand(out, reader, library));
        commandMap.put("Quit", new QuitCommand(menuState));
        return commandMap;
    }

    private static Collection<String> initialBooks() {
        Collection<String> initialBooks = new ArrayList<String>();
        initialBooks.add("The Bible");
        initialBooks.add("Slaughterhouse Five");
        return initialBooks;
    }
}
