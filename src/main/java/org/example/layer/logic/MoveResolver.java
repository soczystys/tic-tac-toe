package org.example.layer.logic;

import org.example.layer.data.Board;
import org.example.layer.presentation.Coordinates;

import java.util.List;

public class MoveResolver {
    public void resolveMove(Board board, Coordinates coordinates, char playerChar) throws CoordinateNotEmptyException {

        if (board.isEmpty(coordinates)) {
            board.setCurrentState(coordinates, playerChar);
        } else {
            throw new CoordinateNotEmptyException();
        }
    }

    public boolean gameWon(Board board, Coordinates coordinates) {
        char playerChar = board.getCurrentState(coordinates);
        int charCountInRow = 0;
        int charCountInCol = 0;
        int x = coordinates.getX();
        int y = coordinates.getY();
        for (int i = 1; i <= 3; i++) {
            if (playerChar == board.getCurrentState(new Coordinates(i, y))) {
                charCountInRow++;
            }
            if (playerChar == board.getCurrentState(new Coordinates(x, i))) {
                charCountInCol++;
            }
        }
        if (charCountInRow >= 3 || charCountInCol >= 3) {
            return true;
        } else if (playerChar == board.getCurrentState(new Coordinates(2, 2))
                && playerChar == board.getCurrentState(new Coordinates(1, 1))
                && playerChar == board.getCurrentState(new Coordinates(3, 3))) {
//            first diagonal
            return true;
        } else if (playerChar == board.getCurrentState(new Coordinates(2, 2))
                && playerChar == board.getCurrentState(new Coordinates(1, 3))
                && playerChar == board.getCurrentState(new Coordinates(3, 1))) {
//            second diagonal
            return true;
        } else {
            return false;
        }

    }

    public boolean determineIfPlayerWon(Board board, char playerChar) {
        var columns = board.toListOfColumns();
        var rows = board.toListOfRows();
        var diagonals = board.getDiagonals();

        int maxCountColumns = characterMaxCount(columns, playerChar);
        int maxCountRows = characterMaxCount(rows, playerChar);
        int maxCountDiagonals = characterMaxCount(diagonals, playerChar);

        if (maxCountRows == 3 || maxCountColumns == 3 || maxCountDiagonals == 3) {
            return true;
        } else {
            return false;
        }
    }

    int characterMaxCount(List<List<Character>> bigList, char playerChar) {
        int count = 0;
        for (List<Character> list: bigList) {
            int currentCount = 0;
            for (Character character :
                    list) {
                if (character == playerChar) {
                    currentCount++;
                }
            }
            if (currentCount > count) {
                count = currentCount;
            }
        }
        return count;
    }

    public boolean allCoordinatesTaken(Board board) {
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                if (board.isEmpty(new Coordinates(i, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
