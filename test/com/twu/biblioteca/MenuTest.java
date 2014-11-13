package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream printStream;
    private Menu menu;
    private BufferedReader reader;
    private Command command;
    private Map<String,Command> commandMap;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        command = mock(Command.class);
        commandMap = new HashMap<String, Command>();
        menu = new Menu(printStream, reader, commandMap, new AtomicBoolean(true));
    }

    @Test
    public void shouldPrintOptions(){
        String input = "1";
        String commandName = "A Command Name";
        commandMap.put(input, command);
        when(command.commandName()).thenReturn(commandName);
        menu.printOptions();
        verify(printStream).println("1) A Command Name");
    }

    @Test
    public void shouldCallCommandWhenOptionIsSelected() throws IOException {
        String input = "1";
        commandMap.put(input, command);
        when(reader.readLine()).thenReturn(input);
        menu.selectOption();
        verify(command).execute();
    }

    @Test
    public void shouldRePromptWhenGivenInvalidOption() throws IOException {
        when(reader.readLine()).thenReturn("Bad Input");
        menu.selectOption();
        verify(printStream).println("Select a valid option!");
    }

    @Test
    public void shouldBeDoneAfterReceivingQuit() throws IOException {
        when(reader.readLine()).thenReturn("Quit");
        menu.selectOption();
        assertFalse(menu.shouldContinue());
    }

    @Test
    public void ShouldNotBeDoneIfQuitNotReceived() throws IOException {
        when(reader.readLine()).thenReturn("1");
        menu.selectOption();
        assertTrue(menu.shouldContinue());
    }
}