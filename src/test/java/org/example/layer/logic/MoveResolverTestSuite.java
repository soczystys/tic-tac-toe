package org.example.layer.logic;

import org.example.layer.data.Board;
import org.example.layer.presentation.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveResolverTestSuite {
    @Nested
    class determineIfPlayerWonTest {

        @Test
        void testColumnsNoughtsWon() {
            Board board = new Board();
            MoveResolver moveResolver = new MoveResolver();
            board.setCurrentState(new Coordinates(1, 1), 'o');
            board.setCurrentState(new Coordinates(1, 2), 'o');
            board.setCurrentState(new Coordinates(1, 3), 'o');

            assertTrue(moveResolver.determineIfPlayerWon(board, 'o'));
            assertFalse(moveResolver.determineIfPlayerWon(board, 'x'));
        }

        @Test
        void testColumnsCrossesWon() {
            Board board = new Board();
            MoveResolver moveResolver = new MoveResolver();
            board.setCurrentState(new Coordinates(1, 1), 'x');
            board.setCurrentState(new Coordinates(1, 2), 'x');
            board.setCurrentState(new Coordinates(1, 3), 'x');

            assertTrue(moveResolver.determineIfPlayerWon(board, 'x'));
            assertFalse(moveResolver.determineIfPlayerWon(board, 'o'));
        }

        @Test
        void testRowsNoughtsWon() {
            Board board = new Board();
            MoveResolver moveResolver = new MoveResolver();
            board.setCurrentState(new Coordinates(1, 1), 'o');
            board.setCurrentState(new Coordinates(2, 1), 'o');
            board.setCurrentState(new Coordinates(3, 1), 'o');

            assertTrue(moveResolver.determineIfPlayerWon(board, 'o'));
            assertFalse(moveResolver.determineIfPlayerWon(board, 'x'));
        }

        @Test
        void testRowsCrossesWon() {
            Board board = new Board();
            MoveResolver moveResolver = new MoveResolver();
            board.setCurrentState(new Coordinates(1, 1), 'x');
            board.setCurrentState(new Coordinates(2, 1), 'x');
            board.setCurrentState(new Coordinates(3, 1), 'x');

            assertTrue(moveResolver.determineIfPlayerWon(board, 'x'));
            assertFalse(moveResolver.determineIfPlayerWon(board, 'o'));
        }

        @Test
        void testDiagonalsNoughtsWon() {
            Board board = new Board();
            MoveResolver moveResolver = new MoveResolver();
            board.setCurrentState(new Coordinates(1, 3), 'o');
            board.setCurrentState(new Coordinates(2, 2), 'o');
            board.setCurrentState(new Coordinates(3, 1), 'o');

            assertTrue(moveResolver.determineIfPlayerWon(board, 'o'));
            assertFalse(moveResolver.determineIfPlayerWon(board, 'x'));
        }

        @Test
        void testDiagonalsCrossesWon() {
            Board board = new Board();
            MoveResolver moveResolver = new MoveResolver();
            board.setCurrentState(new Coordinates(1, 1), 'x');
            board.setCurrentState(new Coordinates(2, 2), 'x');
            board.setCurrentState(new Coordinates(3, 3), 'x');

            assertTrue(moveResolver.determineIfPlayerWon(board, 'x'));
            assertFalse(moveResolver.determineIfPlayerWon(board, 'o'));
        }

    }

    @Test
    void characterMaxCountTest() {
        Board board = new Board();
        board.setCurrentState(new Coordinates(1,1), 'o');
        board.setCurrentState(new Coordinates(3,1), 'o');
        board.setCurrentState(new Coordinates(2,2), 'x');

        List<List<Character>> rows = board.toListOfRows();

        MoveResolver moveResolver = new MoveResolver();

        int result1 = moveResolver.characterMaxCount(rows, 'o');
        int result2 = moveResolver.characterMaxCount(rows, 'x');

        assertEquals(2, result1);
        assertEquals(1, result2);
    }

}
