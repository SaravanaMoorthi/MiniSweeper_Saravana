package com.minesweeper;

import com.minesweeper.domain.Minefield;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleGameTest {

    @Test
    void endToEndWinFlowWithNoMines() {
        String input = String.join(System.lineSeparator(), "2", "0", "A1", "exit");
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(out);

        MinefieldFactory factory = Minefield::new;
        ConsoleGame game = new ConsoleGame(in, printStream, factory);
        game.run();

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("This square contains 0 adjacent mines."));
        assertTrue(output.contains("Here is your updated minefield:"));
        assertTrue(output.contains("Congratulations, you have won the game!"));
    }
}
