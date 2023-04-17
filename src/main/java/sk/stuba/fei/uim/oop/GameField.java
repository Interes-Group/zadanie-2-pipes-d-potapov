package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cells.ArchedPipe;
import sk.stuba.fei.uim.oop.cells.Cell;
import sk.stuba.fei.uim.oop.cells.StraightPipe;
import sk.stuba.fei.uim.oop.cells.OutermostPipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class GameField extends JPanel {
    private final int fieldSize;
    private final Random random;
    private boolean finishWasFound = false;
    private Cell[][] path;
    private OutermostPipe startCell;
    private OutermostPipe finishCell;

    public GameField(int fieldSize) {
        this.fieldSize = fieldSize;
        random = new Random();
        setLayout(new GridLayout(fieldSize, fieldSize));
        generatePath();
        addCellsToGameField();
    }

    private void generatePath() {
        path = new Cell[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                path[i][j] = null;
            }
        }
        generateStartAndFinish();
        randomizedDFS(null, startCell.getCoordinates());
    }

    private void generateStartAndFinish() {
        int yStart = random.nextInt(fieldSize);
        startCell = new OutermostPipe(0, yStart);
        startCell.waterReached();
        path[0][yStart] = startCell;

        int yFinish = random.nextInt(fieldSize);
        finishCell = new OutermostPipe(fieldSize - 1, yFinish);
        path[fieldSize - 1][yFinish] = finishCell;
    }

    private void randomizedDFS(int[] coordinatesPrevious, int[] coordinatesCurrent) {
        int xCurrent = coordinatesCurrent[0];
        int yCurrent = coordinatesCurrent[1];

        if (coordinatesPrevious != null) {
            path[xCurrent][yCurrent] = new Cell(xCurrent, yCurrent);
        }

        List<int[]> neighbors = new ArrayList<>();
        neighbors.add(new int[]{xCurrent, yCurrent + 1});
        neighbors.add(new int[]{xCurrent, yCurrent - 1});
        neighbors.add(new int[]{xCurrent + 1, yCurrent});
        neighbors.add(new int[]{xCurrent - 1, yCurrent});
        Collections.shuffle(neighbors);

        if (contains(finishCell.getCoordinates(), neighbors)) {
            finishWasFound = true;
            if (coordinatesPrevious != null) {
                chooseAndInsertPipeToPath(coordinatesPrevious, coordinatesCurrent, finishCell.getCoordinates());
            }
            return;
        }

        for (int[] neighbor : neighbors) {
            int xNeighbor = neighbor[0];
            int yNeighbor = neighbor[1];
            if (xNeighbor >= 0 && yNeighbor >= 0 && xNeighbor < fieldSize && yNeighbor < fieldSize && path[xNeighbor][yNeighbor] == null) {
                randomizedDFS(coordinatesCurrent, neighbor);
                if (finishWasFound) {
                    if (coordinatesPrevious != null) {
                        chooseAndInsertPipeToPath(coordinatesPrevious, coordinatesCurrent, neighbor);
                    }
                    return;
                }
            }
        }
    }

    private boolean contains(int[] coordinates, List<int[]> array) {
        for (int[] coordinatesArray : array) {
            if (Arrays.equals(coordinatesArray, coordinates)) {
                return true;
            }
        }
        return false;
    }

    private void chooseAndInsertPipeToPath(int[] coordinatesPrevious, int[] coordinatesCurrent, int[] coordinatesNext) {
        int xPrevious = coordinatesPrevious[0];
        int yPrevious = coordinatesPrevious[1];
        int xNext = coordinatesNext[0];
        int yNext = coordinatesNext[1];

        if (xPrevious != xNext && yPrevious != yNext) {
            insertPipeToPath(new ArchedPipe(coordinatesCurrent[0], coordinatesCurrent[1]));
        } else {
            insertPipeToPath(new StraightPipe(coordinatesCurrent[0], coordinatesCurrent[1]));
        }

    }

    private void insertPipeToPath(Cell pipe) {
        int[] coordinates = pipe.getCoordinates();
        int x = coordinates[0];
        int y = coordinates[1];
        path[x][y] = pipe;
    }

    private void addCellsToGameField(){
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (path[i][j] != null) {
                    add(path[i][j]);
                } else {
                    add(new Cell(i, j));
                }
            }
        }
    }

    public void checkPathFromStart(){

    }
}
