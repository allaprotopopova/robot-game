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
        input("PLACE 1,2,EAST\n" +
                "REPORT\n" +
                "MOVE\n" +
                "MOVE\n" +
                "LEFT\n" +
                "MOVE\n" +
                "REPORT\n" +
                "EXIT");
        Main.main(new String[0]);

        assertEquals("1,2,EAST\n" +
                "3,3,NORTH", outputStreamCaptor.toString().replaceAll("\r", "").trim());
    }

    @Test
    void ignoreCommandsBeforePlace() throws IOException {
        input("MOVE\n" +
                "LEFT\n" +
                "RIGHT\n" +
                "REPORT\n" +
        "PLACE 1,1,WEST\n" +
                "REPORT\n" +
                "EXIT");
        Main.main(new String[0]);
        assertEquals("1,1,WEST", outputStreamCaptor.toString().trim());
    }

    @Test
    void preventInvalidPlacing() throws IOException {
        input("PLACE 4,4,NORTH\n" +
                "REPORT\n" +
                "PLACE 6,8,NORTH\n" +
                "REPORT\n" +
                "EXIT");
        Main.main(new String[0]);
        assertEquals("4,4,NORTH\n" +
                "4,4,NORTH", outputStreamCaptor.toString().replaceAll("\r", "").trim());
    }


    private void input(String line) {
        ByteArrayInputStream in = new ByteArrayInputStream(line.getBytes());
        System.setIn(in);
    }
}