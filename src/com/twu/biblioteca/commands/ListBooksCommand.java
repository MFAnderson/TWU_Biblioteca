package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Library;

public class ListBooksCommand implements Command {
    private final Library library;

    public ListBooksCommand(Library library) {
        this.library = library;
    }

    @Override
    public void execute() {
        library.listBooks();
    }

    @Override
    public String commandName() {
        return "List books";
    }
}
