package sk.stuba.fei.uim.oop.cells;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cell extends JPanel {
    private BufferedImage pic;
    public Cell() {

        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        setBackground(Color.WHITE);
//        try {
//            pic = ImageIO.read(Cell.class.getResourceAsStream("/FEI.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        JLabel picLabel = new JLabel(new ImageIcon(pic));
//        this.add(picLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(pic, 0 ,0 , this);
    }
}