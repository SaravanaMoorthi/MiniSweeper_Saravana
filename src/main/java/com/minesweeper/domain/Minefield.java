package com.minesweeper.domain;

import java.util.Random;

public class Minefield {
    private final int size;
    private final Square[][] grid;
    private final int totalMines;
    private int revealedCount = 0;

    private final Random random;

    public Minefield(int size, int mines) {
        this(size, mines, null);
    }

    public Minefield(int size, int mines, Random random) {
        this.size = size;
        this.totalMines = mines;
        this.grid = new Square[size][size];
        this.random = random == null ? new Random() : random;
        initializeGrid();
        placeMines();
        calculateNumbers();
    }

    private void initializeGrid() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = new Square(false);
            }
        }
    }

    private void placeMines() {
        int placed = 0;
        while (placed < totalMines) {
            int r = random.nextInt(size);
            int c = random.nextInt(size);
            if (!grid[r][c].isMine()) {
                grid[r][c] = new Square(true);
                placed++;
            }
        }
    }

    private void calculateNumbers() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (!grid[r][c].isMine()) {
                    grid[r][c].setAdjacentMines(countAdjacent(r, c));
                }
            }
        }
    }

    private int countAdjacent(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (isValid(row + i, col + j) && grid[row + i][col + j].isMine()) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean isGameWon() {
        return (size * size) - revealedCount == totalMines;
    }

    public boolean isValid(int r, int c) {
        return r >= 0 && r < size && c >= 0 && c < size;
    }

    public Square getSquare(int r, int c) { return grid[r][c]; }
    public int getSize() { return size; }

    public boolean reveal(int r, int c) {
        if (!isValid(r, c) || grid[r][c].isRevealed()) return false;

        grid[r][c].reveal();

        if (grid[r][c].isMine()) return true;

        revealedCount++;

        if (grid[r][c].getAdjacentMines() == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nr = r + i;
                    int nc = c + j;
                    if (isValid(nr, nc) && !grid[nr][nc].isRevealed()) {
                        reveal(nr, nc);
                    }
                }
            }
        }
        return false;
    }
}
