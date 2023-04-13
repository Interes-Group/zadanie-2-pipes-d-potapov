package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

        GameLogic gameLogic = new GameLogic(frame);
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
        buttonRestart.setFocusable(false);

        JPanel topMenu = new JPanel();
        topMenu.setLayout(new GridLayout(2, 2));
        topMenu.add(buttonRestart);
        topMenu.add(buttonCheckWin);
        topMenu.add(gameLogic.getLabel());
        topMenu.add(slider);

        frame.add(topMenu, BorderLayout.PAGE_START);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
