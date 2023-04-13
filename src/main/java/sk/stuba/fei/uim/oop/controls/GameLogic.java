package sk.stuba.fei.uim.oop.controls;

import sk.stuba.fei.uim.oop.GameField;
import sk.stuba.fei.uim.oop.cells.StraightPipe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    JFrame gameFrame;
    GameField gameField;
    int fieldSize;
    public GameLogic(JFrame frame){
        this.gameFrame = frame;
        fieldSize = 8;
        initNewField();
        frame.add(gameField);
    }
    private void initNewField(){
        gameField = new GameField(fieldSize);
        gameField.addMouseMotionListener(this);
        gameField.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Component current = this.gameField.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof StraightPipe)) {
            return;
        }
        ((StraightPipe) current).rotate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        fieldSize = ((JSlider) e.getSource()).getValue();
        restartGame();
        gameFrame.setFocusable(true);
        gameFrame.requestFocus();
    }

    public void restartGame(){
        gameFrame.remove(gameField);
        initNewField();
        gameFrame.add(gameField);
        gameFrame.repaint();
    }
}
