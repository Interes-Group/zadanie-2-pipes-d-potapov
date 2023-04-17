package sk.stuba.fei.uim.oop.cells;

import java.awt.*;

public class outermostPipe extends Cell{
    public outermostPipe(int x, int y){
        super(x, y);
        setBackground(Color.GRAY);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillPolygon(new int[] {150 , 200 , 250},new int[] {150, 100, 150},3);

    }
}
