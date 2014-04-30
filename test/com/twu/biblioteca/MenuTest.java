package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MenuTest {

    private PrintStream printStream;
    private Menu menu;
    private Library library;
    private BufferedReader reader;

    @Before
    public void setUp() throws Exception {
        printStream = mock(PrintStream.class);
        library = mock(Library.class);
        reader = mock(BufferedReader.class);
        menu = new Menu(printStream, library, reader);
    }

    @Test
    public void shouldPrintAnOption(){
        menu.printOptions();
        verify(printStream).println("1) List Books");
    }

    @Test
    public void shouldListBooksWhenGivenOne() throws IOException {
        when(reader.readLine()).thenReturn("1");
        menu.doSomething();
        verify(reader).readLine();
        verify(library).listBooks();
    }

    @Test
    public void shouldRePromptWhenGivenInvalidOption() throws IOException {
        when(reader.readLine()).thenReturn("argleflarble");
        menu.doSomething();
        verify(printStream).println("Select a valid option!");
    }

    @Test
    public void shouldNotListBooksUnlessGiven1() throws IOException {
        when(reader.readLine()).thenReturn("-1");
        menu.doSomething();
        verify(library, never()).listBooks();
    }

    @Test
    public void shouldBeDoneAfterReceivingQuit() throws IOException {
        when(reader.readLine()).thenReturn("Quit");
        menu.doSomething();
        assertFalse(menu.shouldContinue());
    }

    @Test
    public void ShouldNotBeDoneIfQuitNotReceived() throws IOException {
        when(reader.readLine()).thenReturn("1");
        menu.doSomething();
        assertTrue(menu.shouldContinue());
    }

    @Test
    public void shouldProvideCheckoutOption() {
        menu.printOptions();
        verify(printStream).println("2) Check out book");
    }

    @Test
    public void shouldAskWhichBookWhenCheckingOut() throws IOException {
        when(reader.readLine()).thenReturn("2");
        menu.doSomething();
        verify(printStream).println("Which book would you like to check out?");
    }

    @Test
    public void shouldCheckOutSelectedBook() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("aaa");
        menu.doSomething();
        verify(library).checkout("aaa");
    }

    @Test
    public void shouldInformOfSuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("aaa");
        when(library.checkout("aaa")).thenReturn(true);
        menu.doSomething();
        verify(printStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldInformOfUnsuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("aaa");
        when(library.checkout("aaa")).thenReturn(false);
        menu.doSomething();
        verify(printStream).println("That book is not available.");
    }
}