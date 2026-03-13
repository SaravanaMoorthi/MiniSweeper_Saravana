package com.minesweeper;

import com.minesweeper.domain.Minefield;

@FunctionalInterface
public interface MinefieldFactory {
    Minefield create(int size, int mines);
}
