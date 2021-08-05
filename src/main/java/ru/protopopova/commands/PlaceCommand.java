package ru.protopopova.commands;

import ru.protopopova.Direction;
import ru.protopopova.Robot;

/**
 * Place a robot on a tabletop
 */
public class PlaceCommand implements Command {

    private final String x;
    private final String y;
    private final String head;

    public PlaceCommand(String input) {
        String[] coordinates = input.toUpperCase().replace(",", " ").split(" ");
        this.x = coordinates[1];
        this.y = coordinates[2];
        this.head = coordinates[3];
    }

    @Override
    public void execute(Robot robot) {

        try {
            int newX = Integer.parseInt(x);
            int newY = Integer.parseInt(y);
            Direction newHead = Direction.valueOf(head);
            if (robot.isOnTheTable(newX, newY)) {
                robot.setX(newX);
                robot.setY(newY);
                robot.setHead(newHead);
                robot.setActivated(true);
            }
        } catch (Exception e) {
            System.out.println("invalid PLACE command");
        }
    }

}
