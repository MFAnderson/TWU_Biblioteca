package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Collection;


public class Library {

    private final Collection<String> books;
    private final PrintStream printStream;
    private final StringJoiner joiner;
    private final Collection<String> checkedOutBooks;

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
        boolean belongsToLibrary = checkedOutBooks.remove(book);
        if (belongsToLibrary) {
            books.add(book);
        }
        return belongsToLibrary;
    }
}
