package org.example.layer.data;

import org.example.layer.presentation.Coordinates;

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
}
