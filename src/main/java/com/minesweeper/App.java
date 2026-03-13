package com.minesweeper;

public class App {
    public static void main(String[] args) {
        ConsoleGame game = new ConsoleGame(System.in, System.out);
        game.run();
    }
}
