package org.example.layer.presentation;

import org.example.layer.data.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandLineInterfaceHandlerTestSuite {

    @Test
    void getAvailableMovesTest() {
        CommandLineInterfaceHandler commandLineInterfaceHandler = new CommandLineInterfaceHandler();
        Board board = new Board();

        var result1 = commandLineInterfaceHandler.getAvailableMoves(board);
//        System.out.println(result1);

        board.setCurrentState(new Coordinates(2, 2), 'o');
        board.setCurrentState(new Coordinates(1, 1), 'x');

        var result2 = commandLineInterfaceHandler.getAvailableMoves(board);
//        System.out.println(result2);

        Assertions.assertEquals(9, result1.size());
        Assertions.assertEquals(7, result2.size());

    }

}
