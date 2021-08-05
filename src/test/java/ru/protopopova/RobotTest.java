package ru.protopopova;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.protopopova.Direction.*;

class RobotTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private Robot robot;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        robot = initRobot();
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void reportCoordinates() {
        robot.doCommand("REPORT");
        assertEquals("0,0,NORTH", outputStreamCaptor.toString().trim());
    }

    @Test
    void moveNorth() {
        robot.doCommand("PLACE 0,0,NORTH");
        robot.doCommand("MOVE");
        assertRobotPosition(0, 1, NORTH, robot);
    }

    @Test
    void moveEast() {
        robot.doCommand("PLACE 3,0,EAST");
        robot.doCommand("MOVE");
        assertRobotPosition(4, 0, EAST, robot);
    }

    @Test
    void moveSouth() {
        robot.doCommand("PLACE 0,4,SOUTH");
        robot.doCommand("MOVE");
        assertRobotPosition(0, 3, SOUTH, robot);
    }

    @Test
    void moveWest() {
        robot.doCommand("PLACE 4,0,WEST");
        robot.doCommand("MOVE");
        assertRobotPosition(3, 0, WEST, robot);
    }

    @Test
    void doComandIgnoreCase() {
        robot.doCommand("place 4,0,west");
        robot.doCommand("right");
        robot.doCommand("move");
        assertRobotPosition(4, 1, NORTH, robot);
    }

    @Test
    void ignorInvalidMove() {
        robot.doCommand("PLACE 0,0,WEST");
        robot.doCommand("MOVE");
        assertRobotPosition(0, 0, WEST, robot);

        robot.doCommand("PLACE 0,0,SOUTH");
        robot.doCommand("MOVE");
        assertRobotPosition(0, 0, SOUTH, robot);

        robot.doCommand("PLACE 4,4,NORTH");
        robot.doCommand("MOVE");
        assertRobotPosition(4, 4, NORTH, robot);

        robot.doCommand("PLACE 4,4,EAST");
        robot.doCommand("MOVE");
        assertRobotPosition(4, 4, EAST, robot);
    }

    @Test
    void rotateLeft() {

        robot.doCommand("LEFT");
        assertEquals(WEST, robot.getHead());

        robot.doCommand("LEFT");
        assertEquals(SOUTH, robot.getHead());

        robot.doCommand("LEFT");
        assertEquals(EAST, robot.getHead());

        robot.doCommand("LEFT");
        assertEquals(NORTH, robot.getHead());
    }

    @Test
    void rotateRight() {

        robot.doCommand("RIGHT");
        assertEquals(EAST, robot.getHead());

        robot.doCommand("RIGHT");
        assertEquals(SOUTH, robot.getHead());

        robot.doCommand("RIGHT");
        assertEquals(WEST, robot.getHead());

        robot.doCommand("RIGHT");
        assertEquals(NORTH, robot.getHead());
    }

    @Test
    void place() {
        robot = new Robot(new Table());
        assertInitialPosition();
        robot.doCommand("PLACE 2,3,WEST");
        assertRobotPosition(2, 3, WEST, robot);
    }

    @Test
    void placeTwice() {
        assertRobotPosition(0, 0, NORTH, robot);
        robot.doCommand("PLACE 1,2,SOUTH");
        assertRobotPosition(1, 2, SOUTH, robot);
    }

    @Test
    void invalidPlaceCommand() {
        robot = new Robot();
        robot.setTable(new Table());
        assertInitialPosition();

        robot.doCommand("PLACE 1,6,WEST");
        assertInitialPosition();

        robot.doCommand("PLACE 6,2,WEST");
        assertInitialPosition();

        robot.doCommand("PLACE -1,2,WEST");
        assertInitialPosition();

        robot.doCommand("PLACE 1,-2,WEST");
        assertInitialPosition();

        robot.doCommand("PLACE -3,-4,WEST");
        assertInitialPosition();

        robot.doCommand("PLACE 7,8,WEST");
        assertInitialPosition();

        robot.doCommand("PLACE 3,2,WEST1");
        assertInitialPosition();
    }

    @Test
    void clearPositionIfTableRemoved() {
        robot.doCommand("PLACE 1,2,WEST");
        assertRobotPosition(1, 2, WEST, robot);
        robot.setTable(null);
        assertInitialPosition();
    }

    @Test
    void ignoreCommandsIfNotPlaced() {

        robot = new Robot();
        assertInitialPosition();

        robot.doCommand("MOVE");
        assertInitialPosition();

        robot.doCommand("LEFT");
        assertInitialPosition();

        robot.doCommand("RIGHT");
        assertInitialPosition();

        robot.doCommand("REPORT");
        assertEquals("", outputStreamCaptor.toString().trim());

    }

    @Test
    void getCommandsAfterPlaced() {
        robot = new Robot(new Table());
        robot.doCommand("MOVE");
        assertInitialPosition();

        robot.doCommand("PLACE 1,1,NORTH");
        robot.doCommand("MOVE");
        assertRobotPosition(1, 2, NORTH, robot);
    }

    private Robot initRobot() {
        Robot robot = new Robot(new Table());
        robot.doCommand("PLACE " + 0 + "," + 0 + "," + "NORTH");
        robot.setTable(new Table());
        return robot;
    }

    private void assertRobotPosition(int x, int y, Direction head, Robot robot) {
        assertEquals(x, robot.getX());
        assertEquals(y, robot.getY());
        assertEquals(head, robot.getHead());
    }


    private void assertInitialPosition() {
        assertRobotPosition(-1, -1, NONE, robot);
        assertFalse(robot.isActivated());
    }
}