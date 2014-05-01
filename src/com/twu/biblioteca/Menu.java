package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * Created by manderso on 4/29/14.
 */
public class Menu {
    private PrintStream printStream;
    private BufferedReader reader;
    private Map<String, Command> commandMap;
    private boolean shouldContinue = true;

    public Menu(PrintStream printStream, BufferedReader reader, Map<String, Command> commandMap) {
        this.printStream = printStream;
        this.reader = reader;
        this.commandMap = commandMap;
    }

    public void printOptions() {
        printStream.println("Please select an option:");
        for(String input : commandMap.keySet()) {
            printStream.println(input + ") " + commandMap.get(input).commandName());
        }
    }

    public void selectOption() throws IOException {
        String input = reader.readLine();
        if (input.equalsIgnoreCase("Quit")) {
            shouldContinue = false;
        } else if (commandMap.containsKey(input)){
            commandMap.get(input).execute();
        } else {
            printStream.println("Select a valid option!");
        }
    }

    public boolean shouldContinue() {
        return shouldContinue;
    }
}
