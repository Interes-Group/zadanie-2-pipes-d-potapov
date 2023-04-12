package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.controls.GameLogic;

import javax.swing.*;
import java.awt.*;

public class Game {
    public Game() {
        JFrame frame = new JFrame("WaterPipes Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 800);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        GameLogic gameLogic = new GameLogic(frame);
        frame.addKeyListener(gameLogic);

        GameField gameField = new GameField();
        BottomMenu bottomMenu = new BottomMenu(gameLogic);

        frame.add(gameField, BorderLayout.CENTER);
        frame.add(bottomMenu, BorderLayout.PAGE_END);

        frame.setLocationRelativeTo(null); // Позиционируем окно по центру экрана
        frame.setVisible(true);

//        JFrame frame = new JFrame("WaterPipes");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(500,500);
//        frame.setResizable(false);
//        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
//
////        GameLogic logic = new GameLogic();
////        frame.addKeyListener(logic);
////        frame.add(logic.getRender());
//
//        bottomMenu.setLayout(new GridLayout(2, 2));
////        bottomMenu.add(logic.getLabel());
//
//        bottomMenu.add(slider);
//        bottomMenu.add(buttonCheckWin);
//        bottomMenu.add(buttonRestart);
//        frame.add(bottomMenu);

    }
}
