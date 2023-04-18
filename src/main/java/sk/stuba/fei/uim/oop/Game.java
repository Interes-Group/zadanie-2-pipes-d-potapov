package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("WaterPipes Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        JLabel label = new JLabel();
        GameLogic gameLogic = new GameLogic(frame, label);
        frame.addKeyListener(gameLogic);


        JSlider slider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        slider.setMinorTickSpacing(2);
        slider.setMajorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(gameLogic);

        JButton buttonRestart = new JButton("RESTART");
        buttonRestart.addActionListener(gameLogic);
        buttonRestart.setFocusable(false);

        JButton buttonCheckWin = new JButton("CHECK WIN");
        buttonCheckWin.addActionListener(e -> gameLogic.checkWin());
        buttonCheckWin.setFocusable(false);

        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(2, 2));
        menu.add(label);
        menu.add(slider);
        menu.add(buttonRestart);
        menu.add(buttonCheckWin);

        frame.add(menu, BorderLayout.PAGE_END);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
