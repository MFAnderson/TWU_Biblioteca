package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;
import com.twu.biblioteca.Menu;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by manderso on 4/30/14.
 */
public class CheckOutCommand implements Command {
    private Menu menu;

    public CheckOutCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() throws IOException {
        menu.checkoutBook();
    }
}
