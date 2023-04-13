package sk.stuba.fei.uim.oop.cells;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class StraightPipe extends Cell{
    private BufferedImage pic;
    private JLabel picLabel;
    public StraightPipe() {
        try {
            pic = ImageIO.read(Objects.requireNonNull(StraightPipe.class.getResourceAsStream("/straightPipe.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        picLabel = new JLabel(new ImageIcon(pic));
        add(picLabel);
    }

    public void rotate(){
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(90), (double) pic.getWidth() / 2, (double) pic.getHeight() / 2);

        BufferedImage rotatedImage = new BufferedImage(pic.getHeight(), pic.getWidth(), pic.getType());
        Graphics2D g = rotatedImage.createGraphics();
        g.setTransform(transform);
        g.drawImage(pic, 0, 0, null);
        g.dispose();

        pic = rotatedImage;
        picLabel.setIcon(new ImageIcon(pic));
    }
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g.create();
//
//        g2d.setColor(Color.BLACK);
//        g2d.setStroke(new BasicStroke(10));
//
//        int x1 = getWidth() / 2;
//        int y1 = 0;
//
//        int x2 = getWidth() / 2;
//        int y2 = getHeight() / 2;
//        g2d.drawLine(x1, y1, x2, y2);
//
//        int x3 = getWidth() / 2;
//        int y3 = getHeight() / 2;
//
//        int x4 = getWidth();
//        int y4 = getHeight() / 2;
//        g2d.drawLine(x3, y3, x4, y4);
//
//        g2d.dispose();
//    }
}