# Minesweeper (CLI)

## Design
- `Minefield` owns the grid state, mine placement, and recursive reveals.
- `Square` tracks per-cell state and display.
- `ConsoleGame` handles user interaction and game flow; `App` is a thin entry point.
- `MinefieldFactory` allows deterministic creation in tests without changing gameplay.

## Assumptions
- Grid size is a positive integer.
- Mines are capped at 35% of total squares (input above the cap is clamped).
- Coordinates are case-insensitive and formatted like `A1`.

## Requirements
- Java 25
- Maven 3.9+
- OS: Windows, macOS, or Linux

## Run
```bash
mvn -q -DskipTests package
java -cp target/MiniSweeper_Saravana-1.0-SNAPSHOT.jar com.minesweeper.App
```

## Tests
```bash
mvn test
```
