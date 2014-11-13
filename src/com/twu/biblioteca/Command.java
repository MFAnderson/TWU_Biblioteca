package com.twu.biblioteca;

import java.io.IOException;

public interface Command {


    public void execute() throws IOException;
    public String commandName();
}
