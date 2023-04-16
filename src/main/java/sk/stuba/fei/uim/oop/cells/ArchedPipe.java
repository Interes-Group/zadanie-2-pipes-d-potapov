package sk.stuba.fei.uim.oop.cells;

import java.awt.*;


public class ArchedPipe extends Cell{
    private int ind;
    public ArchedPipe() {
        ind = 0;
    }

    @Override
    public void rotate(){
        ind++;
        ind%=4;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final int [][] linesParameters = {
                {getWidth() / 2, 0, 10, getHeight() / 2 + 10},
                {getWidth() / 2, getHeight() / 2, getHeight() / 2, 10},
                {getWidth() / 2, getHeight() / 2, 10, getHeight() / 2},
                {0, getHeight() / 2, getWidth() / 2, 10}
        };
        g.fillRect(linesParameters[ind][0],linesParameters[ind][1],linesParameters[ind][2],linesParameters[ind][3]);
        g.fillRect(linesParameters[(ind+1)%4][0],linesParameters[(ind+1)%4][1],linesParameters[(ind+1)%4][2],linesParameters[(ind+1)%4][3]);
    }
}