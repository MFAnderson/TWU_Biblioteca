package com.twu.biblioteca.commands;

import com.twu.biblioteca.Command;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class QuitCommand implements Command {

    private AtomicBoolean menuState;

    public QuitCommand(AtomicBoolean menuState) {

        this.menuState = menuState;
    }
    @Override
    public void execute() throws IOException {
        menuState.set(false);
    }

    @Override
    public String commandName() {
        return "Quit";
    }
}
