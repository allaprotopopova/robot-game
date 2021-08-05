package ru.protopopova;

import java.util.ArrayList;
import java.util.List;

/**
 * A robot's head possible directions
 */
public enum Direction {

    NORTH,
    SOUTH,
    WEST,
    EAST,
    NONE;

    public static List<Direction> getRotateList() {
        List<Direction> rotateList = new ArrayList<>();
        rotateList.add(NORTH);
        rotateList.add(WEST);
        rotateList.add(SOUTH);
        rotateList.add(EAST);

        return rotateList;
    }
}
