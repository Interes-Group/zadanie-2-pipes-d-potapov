package sk.stuba.fei.uim.oop.cells;

import java.awt.*;


public class StraightPipe extends Cell {
    private int ind;

    public StraightPipe() {
        ind = 0;
    }

    @Override
    public void rotate() {
        ind++;
        ind %= 2;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int[][] linesParameters = {
                {getWidth() / 2, 0, 10, getHeight()},
                {0, getHeight() / 2, getWidth(), 10}
        };
        g.fillRect(linesParameters[ind][0], linesParameters[ind][1], linesParameters[ind][2], linesParameters[ind][3]);
    }
}