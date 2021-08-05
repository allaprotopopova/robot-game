package ru.protopopova.commands;

import ru.protopopova.Robot;

/**
 * An empty command. Use to substitute invalid command
 */
public class IgnoreCommand implements Command {

    @Override
    public void execute(Robot robot) {
    }
}
