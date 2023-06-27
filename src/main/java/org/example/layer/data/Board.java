package org.example.layer.data;

import org.example.layer.presentation.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Board {
    char[][] board = new char[3][3];

    public char getCurrentState(Coordinates coordinates) {
        return board[coordinates.getX() - 1][coordinates.getY() - 1];
    }

    public Board() {
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void setCurrentState(Coordinates coordinates, char playerChar) {
        board [coordinates.getX() - 1] [coordinates.getY() - 1] = playerChar;
    }

    public boolean isEmpty(Coordinates coordinates) {
        return board [coordinates.getX() - 1] [coordinates.getY() - 1] == ' ';
    }

    public List<List<Character>> toListOfColumns() {

        List<List<Character>> columns = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<Character> column = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                column.add(board[i][j]);
            }
            columns.add(column);
        }
        return columns;
    }

    public List<List<Character>> toListOfRows() {
        List<List<Character>> columns = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            List<Character> column = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                column.add(board[j][i]);
            }
            columns.add(column);
        }
        return columns;
    }

    public List<List<Character>> getDiagonals() {
        List<List<Character>> diagonals = new ArrayList<>();

        List<Character> diagonal1 = new ArrayList<>();
        List<Character> diagonal2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            diagonal1.add(board[i][i]);
            diagonal2.add(board[i][2-i]);
        }

        diagonals.add(diagonal1);
        diagonals.add(diagonal2);

        return diagonals;
    }
}
