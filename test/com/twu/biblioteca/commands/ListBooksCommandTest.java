package com.twu.biblioteca.commands;

import com.twu.biblioteca.Library;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ListBooksCommandTest {

    private PrintStream printStream;
    private Library library;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        library = mock(Library.class);
    }
    @Test
    public void shouldPrintJoinedBooks() {
        String joinedBooks = "aaa";
        library.listBooks();
        verify(printStream).println(joinedBooks);
    }
}