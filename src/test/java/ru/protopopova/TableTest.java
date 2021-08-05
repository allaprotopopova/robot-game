package ru.protopopova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void initTable() {
        Table table = new Table();
        assertEquals(4, table.getHeight());
        assertEquals(4, table.getWidth());
    }
}