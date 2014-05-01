package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Library;
import com.twu.biblioteca.Menu;

/**
 * Created by manderso on 4/30/14.
 */
public class ListBooksCommand implements Command {
    private Library library;

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
