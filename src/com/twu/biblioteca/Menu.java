package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Menu {
    private final PrintStream printStream;
    private final BufferedReader reader;
    private final Map<String, Command> commandMap;
    private AtomicBoolean shouldContinue;

    public Menu(PrintStream printStream, BufferedReader reader, Map<String, Command> commandMap, AtomicBoolean shouldContinue) {
        this.printStream = printStream;
        this.reader = reader;
        this.commandMap = commandMap;
        this.shouldContinue = shouldContinue;
    }

    public void printOptions() {
        printStream.println("Please select an option:");
        for(String input : commandMap.keySet()) {
            printStream.println(String.format("%s) %s", input, commandMap.get(input).commandName()));
        }
    }

    public void selectOption() throws IOException {
        String input = reader.readLine();
        try {
            commandMap.get(input).execute();
        } catch (NullPointerException npe) {
            printStream.println("Select a valid option!");

        }
    }

    public boolean shouldContinue() {
        return shouldContinue.get();
    }
}
