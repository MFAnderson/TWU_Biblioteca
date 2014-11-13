package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;

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
    private Collection<String> checkedOutBooks;

    @Before
    public void setUp() throws Exception {
        books = new HashSet<String>();
        printStream = mock(PrintStream.class);
        joiner = mock(StringJoiner.class);
        checkedOutBooks = new HashSet<String>();
        library = new Library(books, printStream, joiner, checkedOutBooks);
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
        String book1 = "aaa";
        String book2 = "bbb";
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

    @Test
    public void shouldReturnTrueOnSuccessfulReturn(){
        checkedOutBooks.add("Book");
        boolean returnMessage = library.returnBook("Book");
        assertTrue(returnMessage);
    }


    @Test
    public void shouldReturnFalseOnUnsuccessfulReturn() {
        boolean returnMessage = library.returnBook("Book Not From Library");
        assertFalse(returnMessage);
    }

    @Test
    public void shouldNotAddNonCheckedOutBookToBooks() {
        library.returnBook("A NOT CHECKED OUT BOOK");
        assertThat(books, not(hasItem("A NOT CHECKED OUT BOOK")));
    }

    @Test
    public void shouldReAddCheckedOutBookToBooks() {
        String book = "A Book";
        books.add(book);
        library.checkout(book);
        library.returnBook(book);
        assertThat(books, hasItem(book));
    }

    @Test
    public void shouldAddToCheckedOutBooksOnCheckOut() {
        books.add("A book");
        library.checkout("A book");
        assertThat(checkedOutBooks, hasItem("A book"));
    }

    @Test
    public void shouldNotHaveReturnedBookInCheckedOutCollection() {
        String book = "A Book";
        books.add(book);
        library.checkout(book);
        library.returnBook(book);
        assertThat(checkedOutBooks, not(hasItem(book)));
    }
}