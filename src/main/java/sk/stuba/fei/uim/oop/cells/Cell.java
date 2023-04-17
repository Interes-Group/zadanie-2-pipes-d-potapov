package sk.stuba.fei.uim.oop.cells;

import sk.stuba.fei.uim.oop.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Cell extends JPanel {
    private final int[] coordinates;
    private List<Direction> directions;
    private boolean isWaterReached = false;
    public Cell(int x, int y) {
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.WHITE);
        coordinates = new int[]{x, y};
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    protected void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public List<Direction> getDirections() {
        return directions;
    }
    public void waterReached(){
        isWaterReached = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isWaterReached){
            g.setColor(Color.BLUE);
        }
        if (directions != null) {
            for (Direction direction : directions) {
                direction.draw(g, getWidth(), getHeight());
            }
        }
    }

    public void rotate() {
        repaint();
    }
}