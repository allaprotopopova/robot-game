package ru.protopopova.commands;

import ru.protopopova.Robot;

import static ru.protopopova.Direction.getRotateList;

/**
 * Rotate a robot to the right
 */
public class RotateRightCommand implements Command {
    @Override
    public void execute(Robot robot) {

        int directionIndex = getRotateList().indexOf(robot.getHead());

        directionIndex--;

        if (directionIndex < 0) {
            directionIndex = getRotateList().size() - 1;
        }
        robot.setHead(getRotateList().get(directionIndex));
    }
}
