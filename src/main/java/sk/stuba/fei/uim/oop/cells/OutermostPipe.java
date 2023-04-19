package sk.stuba.fei.uim.oop.cells;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OutermostPipe extends Cell {
    private final List<Direction> directions;

    public OutermostPipe(int row, int column) {
        super(row, column);
        Random random = new Random();
        directions = new ArrayList<>();
        Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
        directions.add(direction);
        setDirections(directions);
    }

    @Override
    public void rotate() {
        directions.set(0, directions.get(0).next());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = getWidth() / 3;
        g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        if (directions != null) {
            for (Direction direction : directions) {
                direction.draw(g, getWidth(), getHeight());
            }
        }
    }
}
