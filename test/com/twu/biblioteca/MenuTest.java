package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream printStream;
    private Menu menu;
    private Library library;
    private BufferedReader reader;
    private Command command;
    private Map<String,Command> commandMap;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        library = mock(Library.class);
        reader = mock(BufferedReader.class);
        command = mock(Command.class);
        commandMap = new HashMap<String, Command>();
        menu = new Menu(printStream, library, reader, commandMap);
    }

    @Test
    public void shouldPrintOptions(){
        String input = "1";
        String commandName = "A Command Name";
        commandMap.put(input, command);
        when(command.commandName()).thenReturn(commandName);
        menu.printOptions();
        verify(printStream).println("1) A Command Name");
//        verify(printStream).println("1) List Books");
//        verify(printStream).println("2) Check out book");
//        verify(printStream).println("3) Return book");
    }

    @Test
    public void shouldCallCommandWhenOptionIsSelected() throws IOException {
        String input = "1";
        commandMap.put(input, command);
        when(reader.readLine()).thenReturn(input);
        menu.doSomethingWithOptions();
        verify(command).execute();
    }

    @Test
    public void shouldRePromptWhenGivenInvalidOption() throws IOException {
        when(reader.readLine()).thenReturn("argleflarble");
        menu.doSomethingWithOptions();
        verify(printStream).println("Select a valid option!");
    }

    @Test
    public void shouldBeDoneAfterReceivingQuit() throws IOException {
        when(reader.readLine()).thenReturn("Quit");
        menu.doSomethingWithOptions();
        assertFalse(menu.shouldContinue());
    }

    @Test
    public void ShouldNotBeDoneIfQuitNotReceived() throws IOException {
        when(reader.readLine()).thenReturn("1");
        menu.doSomethingWithOptions();
        assertTrue(menu.shouldContinue());
    }

    @Test
    public void shouldProvideCheckoutOption() {
        menu.printOptions();
        verify(printStream).println("2) Check out book");
    }

    @Test
    public void shouldAskWhichBookWhenCheckingOut() throws IOException {
        menu.checkoutBook();
        verify(printStream).println("Which book would you like to check out?");
    }

    @Test
    public void shouldCheckOutSelectedBook() throws IOException {
        when(reader.readLine()).thenReturn("A book");
        menu.checkoutBook();
        verify(library).checkout("A book");
    }

    @Test
    public void shouldInformOfSuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("A book");
        when(library.checkout("A book")).thenReturn(true);
        menu.checkoutBook();
        verify(printStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldInformOfUnsuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("A book");
        when(library.checkout("A book")).thenReturn(false);
        menu.checkoutBook();
        verify(printStream).println("That book is not available.");
    }

    @Test
    public void shouldOfferReturnOption() {
        menu.printOptions();
        verify(printStream).println("3) Return book");
    }

    @Test
    public void shouldAskWhichBookWhenReturningBook() throws IOException {
        menu.returnBook();
        verify(printStream).println("Which book would you like to return?");
    }

    @Test
    public void shouldReturnSpecifiedBookToLibrary() throws IOException {
        String testBook = "A Book!";
        when(reader.readLine()).thenReturn(testBook);
        menu.returnBook();
        verify(library).returnBook(testBook);
    }

    @Test
    public void shouldDisplaySuccessMessageOnSuccessfulReturn() throws IOException {
        when(library.returnBook(anyString())).thenReturn(true);
        menu.returnBook();
        verify(printStream).println("Thank you for returning the book.");
    }

    @Test
    public void shouldDisplayFailureMessageOnFailedReturn() throws IOException {
        when(library.returnBook(anyString())).thenReturn(false);
        menu.returnBook();
        verify(printStream).println("That is not a valid book to return.");
    }
}