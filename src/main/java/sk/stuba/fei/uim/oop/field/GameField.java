package sk.stuba.fei.uim.oop.field;

import sk.stuba.fei.uim.oop.cells.*;

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
        int columnStart = random.nextInt(fieldSize);
        startCell = new OutermostPipe(0, columnStart);
        startCell.setWaterReached(true);
        path[0][columnStart] = startCell;

        int columnFinish = random.nextInt(fieldSize);
        finishCell = new OutermostPipe(fieldSize - 1, columnFinish);
        path[fieldSize - 1][columnFinish] = finishCell;
    }

    private void randomizedDFS(int[] coordinatesPrevious, int[] coordinatesCurrent) {
        int rowCurrent = coordinatesCurrent[0];
        int columnCurrent = coordinatesCurrent[1];

        if (coordinatesPrevious != null) {
            path[rowCurrent][columnCurrent] = new Cell(rowCurrent, columnCurrent);
        }

        List<int[]> neighbors = new ArrayList<>();
        neighbors.add(new int[]{rowCurrent, columnCurrent + 1});
        neighbors.add(new int[]{rowCurrent, columnCurrent - 1});
        neighbors.add(new int[]{rowCurrent + 1, columnCurrent});
        neighbors.add(new int[]{rowCurrent - 1, columnCurrent});
        Collections.shuffle(neighbors);

        if (contains(finishCell.getCoordinates(), neighbors)) {
            finishWasFound = true;
            if (coordinatesPrevious != null) {
                chooseAndInsertPipeToPath(coordinatesPrevious, coordinatesCurrent, finishCell.getCoordinates());
            }
            return;
        }

        for (int[] neighbor : neighbors) {
            int rowNeighbor = neighbor[0];
            int columnNeighbor = neighbor[1];
            boolean isCoordinatesInField = rowNeighbor >= 0 && columnNeighbor >= 0 && rowNeighbor < fieldSize && columnNeighbor < fieldSize;
            if (isCoordinatesInField && path[rowNeighbor][columnNeighbor] == null) {
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
        int rowPrevious = coordinatesPrevious[0];
        int columnPrevious = coordinatesPrevious[1];
        int rowNext = coordinatesNext[0];
        int columnNext = coordinatesNext[1];

        if (rowPrevious != rowNext && columnPrevious != columnNext) {
            insertPipeToPath(new ArchedPipe(coordinatesCurrent[0], coordinatesCurrent[1]));
        } else {
            insertPipeToPath(new StraightPipe(coordinatesCurrent[0], coordinatesCurrent[1]));
        }

    }

    private void insertPipeToPath(Cell pipe) {
        int[] coordinates = pipe.getCoordinates();
        int row = coordinates[0];
        int column = coordinates[1];
        path[row][column] = pipe;
    }

    private void addCellsToGameField() {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (path[i][j] != null) {
                    add(path[i][j]);
                } else {
                    Cell cell = new Cell(i, j);
                    path[i][j] = cell;
                    add(cell);
                }
            }
        }
    }

    public boolean isPathFromStartToFinish() {
        Direction direction = startCell.getDirections().get(0);
        int[] coordinates = direction.getNextCoordinates(startCell.getCoordinates()[0], startCell.getCoordinates()[1]);
        int row = coordinates[0];
        int column = coordinates[1];

        while (row >= 0 && column >= 0 && row < fieldSize && column < fieldSize && path[row][column].isConnectedToDirection(direction)) {
            path[row][column].setWaterReached(true);
            if (path[row][column] == finishCell) {
                return true;
            }
            direction = path[row][column].getExitDirection(direction.next().next());
            coordinates = direction.getNextCoordinates(row, column);
            row = coordinates[0];
            column = coordinates[1];
        }

        return false;
    }

    public void setDefaultColorForPipes() {
        for (Cell[] rowPath : path) {
            for (Cell cell : rowPath) {
                if (cell != startCell) {
                    cell.setWaterReached(false);
                }
            }
        }
    }
}
