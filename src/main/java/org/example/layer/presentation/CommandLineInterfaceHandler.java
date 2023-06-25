package org.example.layer.presentation;

import org.example.layer.data.Board;

import java.util.Scanner;

public final class CommandLineInterfaceHandler {
    Scanner scanner = new Scanner(System.in);

    public Coordinates readNextMove() throws BadCoordinatesException {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        validateMove(x, y);

        return new Coordinates(x, y);
    }

    private void validateMove(int x, int y) throws BadCoordinatesException {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            throw new BadCoordinatesException();
        }
    }

    public void showBoard(Board board) {
        for (int i = 1; i <= 3; i++) {
            System.out.print('|');
            for (int j = 1; j <= 3; j++) {
                System.out.print(board.getCurrentState(new Coordinates(j, i)) + "|");
            }
            System.out.println();
        }
    }
}
