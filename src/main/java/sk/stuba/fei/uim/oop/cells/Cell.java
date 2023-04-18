package sk.stuba.fei.uim.oop.cells;

import lombok.Setter;
import sk.stuba.fei.uim.oop.Direction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Cell extends JPanel {
    private final int[] coordinates;
    private List<Direction> directions;
    private boolean isWaterReached = false;
    @Setter
    private boolean highlight;

    public Cell(int row, int column) {
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.WHITE);
        coordinates = new int[]{row, column};
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    protected void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public void setWaterReached(boolean bool){
        isWaterReached = bool;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (highlight){
            g.setColor(Color.lightGray);
            ((Graphics2D) g).setStroke(new BasicStroke(6));
            g.drawRect(0,0 , getWidth(), getHeight());
            g.setColor(Color.DARK_GRAY);
            highlight = false;
        }
        if (isWaterReached) {
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

    public boolean isConnectedToDirection(Direction direction) {
        if (directions != null) {
            Direction connectDirection = direction.next().next();
            for (Direction tubeDirection : directions) {
                if (connectDirection.equals(tubeDirection)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Direction getExitDirection(Direction direction) {
        int enterDirectionIndex = directions.lastIndexOf(direction);
        return directions.get((enterDirectionIndex + 1) % directions.size());
    }
}