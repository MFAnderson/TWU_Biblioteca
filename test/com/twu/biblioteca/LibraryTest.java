package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LibraryTest {

    private Collection<String> books;
    private Library library;
    private PrintStream printStream;
    private StringJoiner joiner;

    @Before
    public void setUp() throws Exception {
        books = new HashSet<String>();
        printStream = mock(PrintStream.class);
        joiner = mock(StringJoiner.class);
        library = new Library(books, printStream, joiner);
    }


    @Test
    public void shouldJoinBookList() {
        library.listBooks();
        verify(joiner).join(books);
    }

    @Test
    public void shouldPrintJoinedBooks() {
        String joinedBooks = "aaa";
        when(joiner.join(any(Collection.class))).thenReturn(joinedBooks);
        library.listBooks();
        verify(printStream).println(joinedBooks);
    }

    @Test
    public void shouldNotPrintCheckedOutBook() {
        String book1 = new String("aaa");
        String book2 = new String("bbb");
        books.add(book1);
        books.add(book2);
        library.checkout(book1);
        assertThat(books, not(hasItem(book1)));
    }

    @Test
    public void shouldReturnTrueOnSuccessfulCheckout() {
        books.add("a");
        boolean retVal = library.checkout("a");
        assertTrue(retVal);
    }

    @Test
    public void shouldReturnFalseOnFailedCheckout() {
        boolean retVal = library.checkout("A BOOK THAT IS NOT IN THE LIBRARY");
        assertFalse(retVal);

    }
}