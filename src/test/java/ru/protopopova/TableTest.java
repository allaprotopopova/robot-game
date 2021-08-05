package ru.protopopova;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void initTable() {
        Table table = new Table();
        assertEquals(5, table.getHeight());
        assertEquals(5, table.getWidth());
    }
}