package ru.protopopova;


import java.util.*;

import static ru.protopopova.Command.*;
import static ru.protopopova.Direction.*;
import static ru.protopopova.Direction.NONE;

public class Robot {

    private int x = -1;
    private int y = -1;
    private Direction head = NONE;
    private Table table;
    private static final List<Direction> rotateList = new ArrayList<>();


    static {
        rotateList.add(NORTH);
        rotateList.add(WEST);
        rotateList.add(SOUTH);
        rotateList.add(EAST);
    }


    public Robot() {
    }

    public Robot(Table table) {
        setTable(table);
    }

    public void doCommand(String input) {

        String upperCaseInput = input.toUpperCase();

        Command command = parseCommand(upperCaseInput);

        if (!isPlaced() && command != PLACE) {
            return;
        }

        if (MOVE.equals(command)) {
            move();
        }
        if (LEFT.equals(command) || RIGHT.equals(command)) {
            rotate(command);
        }
        if (REPORT.equals(command)) {
            report();
        }

        if (PLACE.equals(command)) {
            String[] coordinates = upperCaseInput.replace(",", " ").split(" ");
            place(coordinates[1], coordinates[2], coordinates[3]);
        }
    }

    private boolean isPlaced() {
        return head != NONE;
    }

    private Command parseCommand(String command) {

        try {
            String[] splitedCommand = command.split(" ");
            return Command.valueOf(splitedCommand[0]);

        } catch (Exception e) {
            return Command.NONE;
        }
    }

    private void report() {

        System.out.printf("%d,%d,%s%n", x, y, head);

    }

    private void move() {

        if (NORTH.equals(head) || SOUTH.equals(head)) {
            moveY();
        }

        if (EAST.equals(head) || WEST.equals(head)) {
            moveX();
        }

    }

    private void moveX() {
        int newX;

        if (EAST.equals(head)) {
            newX = x + 1;
        } else {
            newX = x - 1;
        }
        if (isOnTheTable(newX, table.getWidth())) {
            setX(newX);
        }
    }

    private void moveY() {

        int newY = NORTH == head ? y + 1 : y - 1;
        if (isOnTheTable(newY, table.getHeight())) {
            setY(newY);
        }
    }

    private void rotate(Command direction) {

        int directionIndex = rotateList.indexOf(head);

        if (LEFT.equals(direction)) {
            directionIndex++;
        } else {
            directionIndex--;
        }

        if (directionIndex < 0) {
            directionIndex = rotateList.size() - 1;
        }
        if (directionIndex > rotateList.size() - 1) {
            directionIndex = 0;
        }
        setHead(rotateList.get(directionIndex));
    }

    private void place(String x, String y, String head) {

        if (table == null) {
            return;
        }
        try {
            int newX = Integer.parseInt(x);
            int newY = Integer.parseInt(y);
            Direction newHead = Direction.valueOf(head);
            if (isOnTheTable(newX, table.getWidth()) && (isOnTheTable(newY, table.getHeight()))) {
                setX(newX);
                setY(newY);
                setHead(newHead);

            }
        } catch (Exception e) {
            System.out.println("invalid PLACE command");
        }


    }

    private boolean isOnTheTable(int newCoordinate, int maxValue) {
        return newCoordinate >= 0 && newCoordinate <= maxValue;
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public Direction getHead() {
        return head;
    }

    private void setHead(Direction head) {
        this.head = head;
    }

    public void setTable(Table table) {
        this.table = table;
        if (table == null) {
            setX(-1);
            setY(-1);
            setHead(NONE);
        }
    }
}
