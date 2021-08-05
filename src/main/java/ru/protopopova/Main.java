package ru.protopopova;

import java.io.*;

/**
 * The program takes a user's input and translates it to a robot
 */
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
