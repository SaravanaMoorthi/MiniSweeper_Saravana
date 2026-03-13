package com.minesweeper;

import com.minesweeper.domain.Minefield;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleGame {
    private final Scanner scanner;
    private final PrintStream out;
    private final MinefieldFactory minefieldFactory;

    public ConsoleGame(InputStream input, PrintStream output) {
        this(input, output, Minefield::new);
    }

    public ConsoleGame(InputStream input, PrintStream output, MinefieldFactory minefieldFactory) {
        this.scanner = new Scanner(input);
        this.out = output;
        this.minefieldFactory = minefieldFactory;
    }

    public void run() {
        while (true) {
            runGameSession();
            out.println("\nPress any key to play again...");
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("exit")) {
                break;
            }
        }
    }

    private void runGameSession() {
        out.println("\nWelcome to Minesweeper!");

        out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid):");
        int size = getValidInt();

        int maxMines = (int) (size * size * 0.35);
        out.println("Enter the number of mines to place on the grid (maximum is 35% of the total squares):");
        int mines = Math.min(getValidInt(), maxMines);

        Minefield field = minefieldFactory.create(size, mines);
        boolean gameEnded = false;

        display(field, "Here is your minefield:");

        while (!gameEnded) {
            out.print("Select a square to reveal (e.g. A1): ");
            String input = scanner.next().toUpperCase();

            try {
                if (input.length() < 2) {
                    throw new IllegalArgumentException("Invalid coordinate length.");
                }
                int row = input.charAt(0) - 'A';
                int col = Integer.parseInt(input.substring(1)) - 1;

                if (!field.isValid(row, col)) {
                    out.println("Invalid coordinates. Please stay within the grid.");
                    continue;
                }

                if (field.getSquare(row, col).isRevealed()) {
                    out.println("That square is already revealed. Choose another.");
                    continue;
                }

                if (field.reveal(row, col)) {
                    display(field, "Here is your updated minefield:");
                    out.println("Oh no, you detonated a mine! Game over.");
                    gameEnded = true;
                } else {
                    out.println("This square contains " + field.getSquare(row, col).getAdjacentMines() + " adjacent mines.");
                    out.println();
                    display(field, "Here is your updated minefield:");
                    if (field.isGameWon()) {
                        out.println("Congratulations, you have won the game!");
                        gameEnded = true;
                    }
                }
            } catch (Exception e) {
                out.println("Incorrect format. Please use a letter and a number (e.g., B2).");
            }
        }
    }

    private int getValidInt() {
        while (!scanner.hasNextInt()) {
            out.println("Please enter a valid number:");
            scanner.next();
        }
        return scanner.nextInt();
    }


    private void display(Minefield field, String title) {
        out.println("\n" + title);
        out.print("  ");
        for (int i = 1; i <= field.getSize(); i++) {
            out.print(i + " ");
        }
        out.println();
        for (int r = 0; r < field.getSize(); r++) {
            out.print((char) ('A' + r) + " ");
            for (int c = 0; c < field.getSize(); c++) {
                out.print(field.getSquare(r, c) + " ");
            }
            out.println();
        }
        out.println();
    }
}
