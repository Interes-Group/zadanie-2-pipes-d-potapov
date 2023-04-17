package sk.stuba.fei.uim.oop.cells;

import javax.swing.*;
import java.awt.*;

public class Cell extends JPanel {
    private final int[] coordinates;
    public Cell(int x, int y) {
        coordinates = new int[]{x, y};
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.WHITE);
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public void rotate(){}
}