package ru.protopopova.commands;

import ru.protopopova.Robot;

import static ru.protopopova.Direction.*;
import static ru.protopopova.Direction.WEST;

/**
 * Move a robot for one unit in faced direction
 */
public class MoveCommand implements Command {

    @Override
    public void execute(Robot robot) {

        if (NORTH.equals(robot.getHead()) || SOUTH.equals(robot.getHead())) {

            int newY = NORTH == robot.getHead() ? robot.getY() + 1 : robot.getY() - 1;
            if (robot.isOnTheTable(robot.getX(), newY)) {
                robot.setY(newY);
            }
        }

        if (EAST.equals(robot.getHead()) || WEST.equals(robot.getHead())) {

            int newX = EAST == robot.getHead() ? robot.getX() + 1 : robot.getX() - 1;
            if (robot.isOnTheTable(newX, robot.getY())) {
                robot.setX(newX);
            }
        }

    }

}
