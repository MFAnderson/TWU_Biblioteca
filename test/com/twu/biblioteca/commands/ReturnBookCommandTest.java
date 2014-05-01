package com.twu.biblioteca.commands;

import com.twu.biblioteca.Library;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReturnBookCommandTest {

    private PrintStream printStream;
    private BufferedReader reader;
    private Library library;
    private ReturnBookCommand command;

    @Before
    public void setup() {
        printStream = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        library = mock(Library.class);
        command = new ReturnBookCommand(printStream, reader, library);
    }
    
    @Test
    public void shouldAskWhichBookWhenReturningBook() throws IOException {
        command.execute();
        verify(printStream).println("Which book would you like to return?");
    }

    @Test
    public void shouldReturnSpecifiedBookToLibrary() throws IOException {
        String testBook = "A Book!";
        when(reader.readLine()).thenReturn(testBook);
        command.execute();
        verify(library).returnBook(testBook);
    }

    @Test
    public void shouldDisplaySuccessMessageOnSuccessfulReturn() throws IOException {
        when(library.returnBook(anyString())).thenReturn(true);
        command.execute();
        verify(printStream).println("Thank you for returning the book.");
    }

    @Test
    public void shouldDisplayFailureMessageOnFailedReturn() throws IOException {
        when(library.returnBook(anyString())).thenReturn(false);
        command.execute();
        verify(printStream).println("That is not a valid book to return.");
    }
}