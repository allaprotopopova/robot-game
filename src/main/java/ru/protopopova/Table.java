package ru.protopopova;

/**
 * A tabletop, of dimensions 5 units x 5 units
 * A toy robot moves on a tabletop
 */
public class Table {

    private final int height;
    private final int width;

    public Table() {
        this.height = 4;
        this.width = 4;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
