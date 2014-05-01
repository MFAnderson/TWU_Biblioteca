package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Menu;

/**
 * Created by manderso on 4/30/14.
 */
public class ListBooksCommand implements Command {
    private Menu menu;

    public ListBooksCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.listBooks();
    }

    @Override
    public String commandName() {
        return "List books";
    }
}
