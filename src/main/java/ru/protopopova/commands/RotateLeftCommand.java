package ru.protopopova.commands;

import ru.protopopova.Robot;

import static ru.protopopova.Direction.getRotateList;

/**
 * Rotate a robot to the left
 */
public class RotateLeftCommand implements Command {

    @Override
    public void execute(Robot robot) {

        int directionIndex = getRotateList().indexOf(robot.getHead());

        directionIndex++;

        if (directionIndex > getRotateList().size() - 1) {
            directionIndex = 0;
        }
        robot.setHead(getRotateList().get(directionIndex));

    }
}
