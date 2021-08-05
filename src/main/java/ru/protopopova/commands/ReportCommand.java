package ru.protopopova.commands;

import ru.protopopova.Robot;

/**
 * Report robot's coordinates and face direction
 */
public class ReportCommand implements Command {

    @Override
    public void execute(Robot robot) {

        System.out.printf("%d,%d,%s%n", robot.getX(), robot.getY(), robot.getHead());
    }
}
