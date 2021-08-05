package ru.protopopova;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    private final PrintStream standardInput = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void test() {

        Table table = new Table();
        Robot robot = new Robot(table);
    }
}
