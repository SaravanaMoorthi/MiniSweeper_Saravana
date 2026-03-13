package com.minesweeper.domain;

public class Square {
    private final boolean isMine;
    private boolean revealed = false;
    private int adjacentMines = 0;

    public Square(boolean isMine) {
        this.isMine = isMine;
    }

    public boolean isMine() { return isMine; }
    public boolean isRevealed() { return revealed; }
    public void reveal() { this.revealed = true; }
    public int getAdjacentMines() { return adjacentMines; }
    public void setAdjacentMines(int count) { this.adjacentMines = count; }

    @Override
    public String toString() {
        if (!revealed) return "_";
        return isMine ? "*" : String.valueOf(adjacentMines);
    }
}