package com.twu.biblioteca.commands;

import com.twu.biblioteca.Library;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CheckOutCommandTest {

    private BufferedReader reader;
    private PrintStream printStream;
    private Library library;
    private CheckOutCommand command;

    @Before
    public void setUp() {
        reader = mock(BufferedReader.class);
        printStream = mock(PrintStream.class);
        library = mock(Library.class);
        command = new CheckOutCommand(printStream, reader, library);
    }
    
    @Test
    public void shouldAskWhichBookWhenCheckingOut() throws IOException {
        command.execute();
        verify(printStream).println("Which book would you like to check out?");
    }

    @Test
    public void shouldCheckOutSelectedBook() throws IOException {
        when(reader.readLine()).thenReturn("A book");
        command.execute();
        verify(library).checkout("A book");
    }

    @Test
    public void shouldInformOfSuccessfulCheckout() throws IOException {
        
        when(reader.readLine()).thenReturn("A book");
        when(library.checkout("A book")).thenReturn(true);
        command.execute();
        verify(printStream).println("Thank you! Enjoy the book");
    }

    @Test
    public void shouldInformOfUnsuccessfulCheckout() throws IOException {
        when(reader.readLine()).thenReturn("A book");
        when(library.checkout("A book")).thenReturn(false);
        command.execute();
        verify(printStream).println("That book is not available.");
    }
}