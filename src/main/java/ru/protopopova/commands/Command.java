package ru.protopopova.commands;

import ru.protopopova.Robot;

/**
 * A main interface for commands that a robot can do
 */
public interface Command {
    void execute(Robot robot);
}
