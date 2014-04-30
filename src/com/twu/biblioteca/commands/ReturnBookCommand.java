package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Menu;

import java.io.IOException;

/**
 * Created by manderso on 4/30/14.
 */
public class ReturnBookCommand implements Command{

    private Menu menu;

    public ReturnBookCommand( Menu menu ) {
        this.menu = menu;
    }

    @Override
    public void execute() throws IOException {
        menu.returnBook();
    }
}
