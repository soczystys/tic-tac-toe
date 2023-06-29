package org.example.layer.presentation;

import org.example.layer.data.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public final class CommandLineInterfaceHandler {
    Scanner scanner = new Scanner(System.in);

    public Coordinates readNextMove(Board board) throws BadCoordinatesException {
        System.out.println("enter coordinate x");
        int x = scanner.nextInt();
        System.out.println("enter coordinate y");
        int y = scanner.nextInt();
        validateMove(x, y, board);

        return new Coordinates(x, y);
    }

    public int readNextGame() {
        System.out.println("enter game mode: ");
        System.out.println("1 - 1v1 standard");
        System.out.println("2 - solo standard");
        System.out.println("3 - 1v1 big board");
        int gameOption = scanner.nextInt();
        return gameOption;
    }

    private void validateMove(int x, int y, Board board) throws BadCoordinatesException {
        if (x < 1 || x > board.getSize() || y < 1 || y > board.getSize()) {
            throw new BadCoordinatesException();
        }
    }

    public void showBoard(Board board) {
        for (int i = 1; i <= board.getSize(); i++) {
            System.out.print('|');
            for (int j = 1; j <= board.getSize(); j++) {
                System.out.print(board.getCurrentState(new Coordinates(j, i)) + "|");
            }
            System.out.println();
        }
        System.out.println();
    }

    List<Coordinates> getAvailableMoves(Board board) {

        List<Coordinates> emptyCoordinates = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {

                Coordinates coordinates = new Coordinates(i, j);
                if (board.isEmpty(coordinates)) {
                    emptyCoordinates.add(coordinates);
                }

            }
        }
        return emptyCoordinates;
    }

    public Coordinates pickRandomCoordinates(Board board) {
        List<Coordinates> list = getAvailableMoves(board);
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }
}
