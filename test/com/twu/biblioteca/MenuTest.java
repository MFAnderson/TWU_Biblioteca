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
        menu.doSomethingWithOptions();
        verify(reader).readLine();
        verify(library).listBooks();
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
        when(reader.readLine()).thenReturn("2");
        menu.doSomethingWithOptions();
        verify(printStream).println("Which book would you like to check out?");
    }

    @Test
    public void shouldCheckOutSelectedBook() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("A book");
        menu.doSomethingWithOptions();
        verify(library).checkout("A book");
    }

    @Test
    public void shouldInformOfSuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("A book");
        when(library.checkout("A book")).thenReturn(true);
        menu.doSomethingWithOptions();
        verify(printStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldInformOfUnsuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("2").thenReturn("A book");
        when(library.checkout("A book")).thenReturn(false);
        menu.doSomethingWithOptions();
        verify(printStream).println("That book is not available.");
    }

    @Test
    public void shouldOfferReturnOption() {
        menu.printOptions();
        verify(printStream).println("3) Return book");
    }

    @Test
    public void shouldAskWhichBookWhenReturningBook() throws IOException {
        when(reader.readLine()).thenReturn("3");
        menu.doSomethingWithOptions();
        verify(printStream).println("Which book would you like to return?");
    }

    @Test
    public void shouldReturnSpecifiedBookToLibrary() throws IOException {
        String testBook = "A Book!";
        when(reader.readLine()).thenReturn("3").thenReturn(testBook);
        menu.doSomethingWithOptions();
        verify(library).returnBook(testBook);
    }

    @Test
    public void shouldDisplaySuccessMessageOnSuccessfulReturn() throws IOException {
        when(reader.readLine()).thenReturn("3");
        when(library.returnBook(anyString())).thenReturn(true);
        menu.doSomethingWithOptions();
        verify(printStream).println("Thank you for returning the book.");
    }

    @Test
    public void shouldDisplayFailureMessageOnFailedReturn() throws IOException {
        when(reader.readLine()).thenReturn("3");
        when(library.returnBook(anyString())).thenReturn(false);
        menu.doSomethingWithOptions();
        verify(printStream).println("That is not a valid book to return.");
    }
}