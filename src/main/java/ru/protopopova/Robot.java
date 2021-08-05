package ru.protopopova;


import ru.protopopova.commands.*;

import static ru.protopopova.Direction.NONE;

/**
 * Robot can move on a table
 * Robot holds a list of commands, which it can execute
 */
public class Robot {

    private int x = -1;
    private int y = -1;
    private Direction head = NONE;
    private boolean activated;
    private Table table;

    public Robot() {
    }

    public Robot(Table table) {
        setTable(table);
    }

    /**
     * Method parses and executes propper commands
     * Invalid and unknown command are ignored
     *
     * @param input a command line
     */
    public void doCommand(String input) {

        if (input == null || input.isEmpty()) {
            return;
        }

        Command command = parseCommand(input);

        if (!activated && !isPlaceCommand(command)) {
            return;
        }
        command.execute(this);
    }

    /**
     * If it's a PLACE command return true
     *
     * @param command an instance of command
     * @return
     */
    private boolean isPlaceCommand(Command command) {
        return command instanceof PlaceCommand;
    }

    /**
     * Method parses an input to a valid command
     * If command is not valid return the instance of IgnoreCommand
     *
     * @param input command line
     * @return an instance of Command
     */
    private Command parseCommand(String input) {

        String[] splittedInput = input.toUpperCase().split(" ");
        String command = splittedInput[0];

        switch (command) {
            case "PLACE":
                return new PlaceCommand(input);
            case "MOVE":
                return new MoveCommand();
            case "LEFT":
                return new RotateLeftCommand();
            case "RIGHT":
                return new RotateRightCommand();
            case "REPORT":
                return new ReportCommand();
            default:
                return new IgnoreCommand();
        }
    }

    /**
     * Method validates robot's coordinates
     *
     * @param x a robot's X coordinate
     * @param y a robot's Y coordinate
     * @return if coordinates within a tabletop, return true
     */
    public boolean isOnTheTable(int x, int y) {
        if (table == null) {
            return false;
        }
        return isOnTableX(x) && isOnTableY(y);
    }

    /**
     * Method validates robot's Y coordinate
     *
     * @param y a robot's Y coordinate
     * @return if the coordinate within a tabletop, return true
     */
    private boolean isOnTableY(int y) {
        return y >= 0 && y <= table.getHeight();
    }

    /**
     * Method validates robot's X coordinate
     *
     * @param x a robot's X coordinate
     * @return if the coordinate within a tabletop, return true
     */
    private boolean isOnTableX(int x) {
        return x >= 0 && x <= table.getWidth();
    }

    /**
     * Method sets a tabletop for the robot
     *
     * @param table a new tabletop. If remove a table, then deactivate the robot
     */
    public void setTable(Table table) {
        this.table = table;
        if (table == null) {
            setX(-1);
            setY(-1);
            setHead(NONE);
            setActivated(false);
        }
    }

    /**
     * Getters ans setters
     */
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getHead() {
        return head;
    }

    public void setHead(Direction head) {
        this.head = head;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }
}
