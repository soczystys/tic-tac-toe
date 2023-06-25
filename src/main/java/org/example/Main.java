package org.example;

import org.example.layer.data.Board;
import org.example.layer.logic.CoordinateNotEmptyException;
import org.example.layer.logic.MoveResolver;
import org.example.layer.presentation.BadCoordinatesException;
import org.example.layer.presentation.CommandLineInterfaceHandler;
import org.example.layer.presentation.Coordinates;

public class Main {
    public static void main(String[] args) {
        CommandLineInterfaceHandler commandLineInterfaceHandler = new CommandLineInterfaceHandler();
        Board board = new Board();
        MoveResolver moveResolver = new MoveResolver();
        char playerChar = ' ';
        Coordinates coordinates = new Coordinates(1,1);

        while(true) {
            playerChar = switchPlayerChar(playerChar);
            try {
                coordinates = commandLineInterfaceHandler.readNextMove();
            } catch (BadCoordinatesException e) {
                System.out.println("coordinates x and y should be in range <1,3>");
                playerChar = switchPlayerChar(playerChar);
            }

            try {
                moveResolver.resolveMove(board, coordinates, playerChar);
            } catch (CoordinateNotEmptyException e) {
                System.out.println("coordinate already set");
                playerChar = switchPlayerChar(playerChar);
            }
            commandLineInterfaceHandler.showBoard(board);
            if (moveResolver.gameWon(board, coordinates)) {
                System.out.println("current player won");
                break;
            }else if (moveResolver.allCoordinatesTaken(board)) {
                System.out.println("board is full - you cannot make more moves");
                System.out.println("its draw");
                break;
            }
//            System.out.println("board full: " + moveResolver.allCoordinatesTaken(board));

        }

    }

    private static char switchPlayerChar(char playerChar) {
        if (playerChar == 'o') {
            playerChar = 'x';
        } else {
            playerChar = 'o';
        }
        return playerChar;
    }
}