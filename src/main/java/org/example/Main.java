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
//        Board board = new Board();
        MoveResolver moveResolver = new MoveResolver();
        char playerChar = ' ';
        Coordinates coordinates = new Coordinates(1, 1);
        int gameOption = commandLineInterfaceHandler.readNextGame();
        playGame(gameOption, commandLineInterfaceHandler, moveResolver, playerChar, coordinates);

//        playStandardGame(commandLineInterfaceHandler, board, moveResolver, playerChar, coordinates);

    }

    private static void playGame(int gameOption, CommandLineInterfaceHandler commandLineInterfaceHandler, MoveResolver moveResolver, char playerChar, Coordinates coordinates) {
        if (gameOption == 1) {
            Board board = new Board();
            playStandardGame(commandLineInterfaceHandler, board, moveResolver, playerChar, coordinates, 3);
        } else if (gameOption == 2) {
            Board board = new Board();
            playStandardSoloGame(commandLineInterfaceHandler, board, moveResolver, playerChar, coordinates);
        } else if (gameOption == 3) {
            Board board = new Board(10);
            playStandardGame(commandLineInterfaceHandler, board, moveResolver, playerChar, coordinates, 5);
        }
    }

    private static void playStandardSoloGame(CommandLineInterfaceHandler commandLineInterfaceHandler, Board board, MoveResolver moveResolver, char playerChar, Coordinates coordinates) {
        while (true) {
            playerChar = switchPlayerChar(playerChar);
            if (playerChar == 'o') {
                try {
                    coordinates = commandLineInterfaceHandler.readNextMove(board);
                } catch (BadCoordinatesException e) {
                    System.out.println("coordinates x and y should be in range <1,3>");
                    playerChar = switchPlayerChar(playerChar);
                }
            }

            if (playerChar == 'x') {
                coordinates = commandLineInterfaceHandler.pickRandomCoordinates(board);
                moveResolver.resolveAutoMove(board, coordinates, playerChar);
            } else {
                try {
                    moveResolver.resolveMove(board, coordinates, playerChar);
                } catch (CoordinateNotEmptyException e) {
                    System.out.println("coordinate already set");
                    playerChar = switchPlayerChar(playerChar);
                }
            }


            commandLineInterfaceHandler.showBoard(board);
            if (moveResolver.hasStreak(board, 'o', 3)) {
                System.out.println("player with \'o\' won");
                break;
            } else if (moveResolver.hasStreak(board, 'x', 3)) {
                System.out.println("player with \'x\' won");
                break;
            } else if (moveResolver.allCoordinatesTaken(board)) {
                System.out.println("board is full - you cannot make more moves");
                System.out.println("its a draw");
                break;
            }
        }
    }


    private static void playStandardGame(CommandLineInterfaceHandler commandLineInterfaceHandler, Board board, MoveResolver moveResolver, char playerChar, Coordinates coordinates, int numberRequiredForWin) {
        while (true) {
            playerChar = switchPlayerChar(playerChar);
            try {
                coordinates = commandLineInterfaceHandler.readNextMove(board);
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
            if (moveResolver.hasStreak(board, 'o', numberRequiredForWin)) {
                System.out.println("player with \'o\' won");
                break;
            } else if (moveResolver.hasStreak(board, 'x', numberRequiredForWin)) {
                System.out.println("player with \'x\' won");
                break;
            } else if (moveResolver.allCoordinatesTaken(board)) {
                System.out.println("board is full - you cannot make more moves");
                System.out.println("its a draw");
                break;
            }
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