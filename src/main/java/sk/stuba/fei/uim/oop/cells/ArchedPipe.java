package sk.stuba.fei.uim.oop.cells;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArchedPipe extends Cell {
    private final List<Direction> directions;

    public ArchedPipe(int row, int column) {
        super(row, column);
        Random random = new Random();
        directions = new ArrayList<>();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        directions.add(direction);
        directions.add(directions.get(0).next());
        setDirections(directions);
    }

    @Override
    public void rotate() {
        directions.add(directions.get(1).next());
        directions.remove(0);
        repaint();
    }
}