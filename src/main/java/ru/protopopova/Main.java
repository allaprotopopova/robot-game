package ru.protopopova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        Table table = new Table();
        Robot robot = new Robot(table);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String command = reader.readLine();

            while (!"EXIT".equalsIgnoreCase(command)) {
                robot.doCommand(command);
                command = reader.readLine();
            }
        }


    }
}
