package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by manderso on 4/29/14.
 */
public class Library {

    private Collection<String> books;
    private PrintStream printStream;
    private StringJoiner joiner;
    private Collection<String> checkedOutBooks;

    public Library(Collection<String> initialBooks, PrintStream printStream, StringJoiner joiner, Collection<String> checkedOutBooks) {
        this.books = initialBooks;
        this.printStream = printStream;
        this.joiner = joiner;
        this.checkedOutBooks = checkedOutBooks;
    }

    public void listBooks() {
        String joinedBooks = joiner.join(books);
        printStream.println(joinedBooks);
    }

    public boolean checkout(String book) {

        boolean success = books.remove(book);
        if (success) {
            checkedOutBooks.add(book);
        }
        return success;
    }

    public boolean returnBook(String book) {
        boolean belongsToLibrary = checkedOutBooks.contains(book);
        if (belongsToLibrary) {
            books.add(book);
        }
        return belongsToLibrary;
    }
}
