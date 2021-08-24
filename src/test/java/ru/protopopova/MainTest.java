package ru.protopopova;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    InputStream sysInBackup = System.in;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(sysInBackup);
        System.setOut(standardOut);
    }

    @Test
    void exitProgram() throws IOException {
        input("EXIT");
        Main.main(new String[0]);
        assertTrue(true);
    }


    @Test
    void normalFlow() throws IOException {
        input("""
                PLACE 1,2,EAST
                REPORT
                MOVE
                MOVE
                LEFT
                MOVE
                REPORT
                EXIT""");
        Main.main(new String[0]);
        assertEquals("1,2,EAST\n" +
                "3,3,NORTH", outputStreamCaptor.toString().trim());
    }

    @Test
    void ignoreCommandsBeforePlace() throws IOException {
        input("""
                MOVE
                LEFT
                RIGHT
                REPORT
                PLACE 1,1,WEST
                REPORT
                EXIT""");
        Main.main(new String[0]);
        assertEquals("1,1,WEST", outputStreamCaptor.toString().trim());
    }

    @Test
    void preventInvalidPlacing() throws IOException {
        input("""
                PLACE 4,4,NORTH
                REPORT
                PLACE 6,8,NORTH
                REPORT
                EXIT""");
        Main.main(new String[0]);
        assertEquals("4,4,NORTH\n" +
                "4,4,NORTH", outputStreamCaptor.toString().trim());
    }


    private void input(String line) {
        ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
    }
}