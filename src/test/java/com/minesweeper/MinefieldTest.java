package com.minesweeper;

import com.minesweeper.domain.Minefield;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinefieldTest {

    @Test
    void testGridInitialization() {
        Minefield field = new Minefield(5, 5);
        assertEquals(5, field.getSize());
    }

    @Test
    void testRevealMineReturnsTrue() {
        Minefield field = new Minefield(1, 1);
        assertTrue(field.reveal(0, 0), "Revealing a mine should return true");
    }

    @Test
    void testWinCondition() {
        Minefield field = new Minefield(2, 1);
        int safeRevealed = 0;

        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 2; c++) {
                if (!field.getSquare(r, c).isMine()) {
                    field.reveal(r, c);
                }
            }
        }
        assertTrue(field.isGameWon());
    }

    @Test
    void testRecursiveReveal() {
        Minefield field = new Minefield(3, 0);
        field.reveal(0, 0);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                assertTrue(field.getSquare(r, c).isRevealed());
            }
        }
    }

    @Test
    void testGameResetPossibility() {
        Minefield firstGame = new Minefield(3, 1);
        firstGame.reveal(0, 0);

        Minefield secondGame = new Minefield(3, 1);

        assertNotEquals(firstGame, secondGame);
        assertFalse(secondGame.getSquare(0, 0).isRevealed(), "New game squares should be hidden");
    }
}
