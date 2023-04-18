package sk.stuba.fei.uim.oop.cells;

import sk.stuba.fei.uim.oop.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StraightPipe extends Cell {
    private final List<Direction> directions;

    public StraightPipe(int row, int column) {
        super(row, column);
        Random random = new Random();
        directions = new ArrayList<>();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        directions.add(direction);
        directions.add(directions.get(0).next().next());
        setDirections(directions);
    }

    @Override
    public void rotate() {
        directions.set(0, directions.get(0).next());
        directions.set(1, directions.get(1).next());
        repaint();
    }
}