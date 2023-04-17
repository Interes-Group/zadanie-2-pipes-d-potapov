package sk.stuba.fei.uim.oop;

import java.awt.*;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction next() {
        return values()[(ordinal() + 1) % values().length];
    }
    public void draw(Graphics g, int cellWidth, int cellHeight){
        int width = cellWidth / 6;

        switch (this) {
            case NORTH:
                g.fillRect((cellWidth - width) / 2, 0, width, (cellHeight + width) / 2);
                break;
            case EAST:
                g.fillRect((cellWidth - width) / 2, (cellHeight - width) / 2, cellHeight / 2, width);
                break;
            case SOUTH:
                g.fillRect((cellWidth - width) / 2, cellHeight / 2, width, cellHeight / 2);
                break;
            case WEST:
                g.fillRect(0, (cellHeight - width) / 2, (cellWidth + width) / 2, width);
        }
    }
}