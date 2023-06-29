package org.example.layer.data;

import org.example.layer.presentation.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class Board {
    char[][] board;
    int size;

    public Board(int size) {
        this.size = size;
        board = new char[size][size];
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public Board() {
        this(3);
    }

    public int getSize() {
        return size;
    }

    public char getCurrentState(Coordinates coordinates) {
        if (coordinates.getX() < 1 || coordinates.getX() > size || coordinates.getY() < 1 || coordinates.getY() > size) {
            return ' ';
        } else {
            return board[coordinates.getX() - 1][coordinates.getY() - 1];
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

        for (int i = 0; i < size; i++) {
            List<Character> column = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                column.add(board[i][j]);
            }
            columns.add(column);
        }
        return columns;
    }

    public List<List<Character>> toListOfRows() {
        List<List<Character>> columns = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Character> column = new ArrayList<>();
            for (int j = 0; j < size; j++) {
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

        for (int i = 0; i < size; i++) {
            diagonal1.add(board[i][i]);
            diagonal2.add(board[i][size - i - 1]);
        }

        diagonals.add(diagonal1);
        diagonals.add(diagonal2);

        return diagonals;
    }

    public List<Coordinates> getAllCoordinatesWithCharacter(char playerChar) {
        List<Coordinates> list = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                Coordinates coordinates = new Coordinates(i,j);
                if (getCurrentState(coordinates) == playerChar) {
                    list.add(coordinates);
                }
            }
        }

        return list;
    }
}
