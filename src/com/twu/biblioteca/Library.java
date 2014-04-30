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

    public Library(Collection<String> initialBooks, PrintStream printStream, StringJoiner joiner) {
        this.books = initialBooks;
        this.printStream = printStream;
        this.joiner = joiner;
    }

    public void listBooks() {
        String joinedBooks = joiner.join(books);
        printStream.println(joinedBooks);
    }

    public boolean checkout(String book) {
        return books.remove(book);
    }

    public boolean returnBook(String book) {
        books.add(book);
        return true;
    }
}
