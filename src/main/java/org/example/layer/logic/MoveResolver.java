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

    public boolean determineIfPlayerWon(Board board, char playerChar, int numberRequiredForWin) {
        var columns = board.toListOfColumns();
        var rows = board.toListOfRows();
        var diagonals = board.getDiagonals();

        int maxCountColumns = characterMaxCount(columns, playerChar);
        int maxCountRows = characterMaxCount(rows, playerChar);
        int maxCountDiagonals = characterMaxCount(diagonals, playerChar);

        if (maxCountRows == numberRequiredForWin || maxCountColumns == numberRequiredForWin || maxCountDiagonals == numberRequiredForWin) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasStreak(Board board, char playerChar, int numberRequiredForWin) {
        int toTheRight;
        int toTheLeft;
        int higher;
        int lower;
        int upperLeft;
        int upperRight;
        int bottomLeft;
        int bottomRight;


        List<Coordinates> coordinatesList = board.getAllCoordinatesWithCharacter(playerChar);

        for (Coordinates coordinates: coordinatesList) {
            toTheLeft = 0;
            toTheRight = 0;
            higher = 0;
            lower = 0;
            upperLeft = 0;
            upperRight = 0;
            bottomLeft = 0;
            bottomRight = 0;

            for (int i = 0; i < numberRequiredForWin; i++) {
                int x = coordinates.getX();
                int y = coordinates.getY();

                if (board.getCurrentState(new Coordinates(x + i, y)) == playerChar) {
                    toTheRight++;
                }

                if (board.getCurrentState(new Coordinates(x - i, y)) == playerChar) {
                    toTheLeft++;
                }

                if (board.getCurrentState(new Coordinates(x, y + i)) == playerChar) {
                    higher++;
                }

                if (board.getCurrentState(new Coordinates(x, y - i)) == playerChar) {
                    lower++;
                }

                if (board.getCurrentState(new Coordinates(x + i, y + i)) == playerChar) {
                    bottomRight++;
                }

                if (board.getCurrentState(new Coordinates(x + i, y - i)) == playerChar) {
                    upperRight++;
                }

                if (board.getCurrentState(new Coordinates(x - i, y + i)) == playerChar) {
                    bottomLeft++;
                }

                if (board.getCurrentState(new Coordinates(x - i, y - i)) == playerChar) {
                    upperLeft++;
                }

            }

            if(toTheRight >= numberRequiredForWin
                    || toTheLeft >= numberRequiredForWin
                    || higher >= numberRequiredForWin
                    || lower >= numberRequiredForWin
                    || bottomRight >= numberRequiredForWin
                    || upperRight >= numberRequiredForWin
                    || bottomLeft >= numberRequiredForWin
                    || upperLeft >= numberRequiredForWin) {
                return true;
            }

        }
        return false;
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

    public void resolveAutoMove(Board board, Coordinates coordinates, char playerChar) {
        board.setCurrentState(coordinates, playerChar);
    }
}
