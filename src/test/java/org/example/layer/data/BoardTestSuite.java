package org.example.layer.data;

import org.example.layer.presentation.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTestSuite {

    @Test
    void toListOfRowsTest() {
        Board board = new Board();
        board.setCurrentState(new Coordinates(1,1), 'o');
        board.setCurrentState(new Coordinates(3,1), 'o');
        board.setCurrentState(new Coordinates(2,2), 'x');

        List<Character> row1 = new ArrayList<>();
        row1.add('o');
        row1.add(' ');
        row1.add('o');

        List<Character> row2 = new ArrayList<>();
        row2.add(' ');
        row2.add('x');
        row2.add(' ');

        List<Character> row3 = new ArrayList<>();
        row3.add(' ');
        row3.add(' ');
        row3.add(' ');

        List<List<Character>> rows = new ArrayList<>();
        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        List<List<Character>> result = board.toListOfRows();

        assertEquals(rows, result);
    }

    @Test
    void toListOfColumnsTest() {
        Board board = new Board();
        board.setCurrentState(new Coordinates(1,1), 'o');
        board.setCurrentState(new Coordinates(3,1), 'o');
        board.setCurrentState(new Coordinates(2,2), 'x');

        List<Character> col1 = new ArrayList<>();
        col1.add('o');
        col1.add(' ');
        col1.add(' ');

        List<Character> col2 = new ArrayList<>();
        col2.add(' ');
        col2.add('x');
        col2.add(' ');

        List<Character> col3 = new ArrayList<>();
        col3.add('o');
        col3.add(' ');
        col3.add(' ');

        List<List<Character>> cols = new ArrayList<>();
        cols.add(col1);
        cols.add(col2);
        cols.add(col3);

        List<List<Character>> result = board.toListOfColumns();

        assertEquals(cols, result);
    }

    @Test
    void getDiagonalsTest() {
        Board board = new Board();
        board.setCurrentState(new Coordinates(1,1), 'o');
        board.setCurrentState(new Coordinates(3,1), 'o');
        board.setCurrentState(new Coordinates(2,2), 'x');

        List<Character> diagonal1 = new ArrayList<>();
        diagonal1.add('o');
        diagonal1.add('x');
        diagonal1.add(' ');

        List<Character> diagonal2 = new ArrayList<>();
        diagonal2.add(' ');
        diagonal2.add('x');
        diagonal2.add('o');

        List<List<Character>> diagonals = new ArrayList<>();
        diagonals.add(diagonal1);
        diagonals.add(diagonal2);


        List<List<Character>> result = board.getDiagonals();

        assertEquals(diagonals, result);
    }
}
