package sk.stuba.fei.uim.oop.controls;

import sk.stuba.fei.uim.oop.GameField;
import sk.stuba.fei.uim.oop.cells.Cell;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 6;
    private JFrame mainGame;
    private JLabel label;
    private GameField currentBoard;
    private int currentBoardSize;

    public GameLogic(JFrame mainGame, JLabel label) {
        this.mainGame = mainGame;
        this.label = label;
        currentBoardSize = INITIAL_BOARD_SIZE;
        initNewField(currentBoardSize);
        mainGame.add(currentBoard);
        updateLabel();
    }

        private void updateLabel() {
        label.setText("BABABABA CURRENT BOARD SIZE: " + currentBoardSize);
        mainGame.revalidate();
        mainGame.repaint();
    }
    private void initNewField(int size) {
        currentBoard = new GameField(size);
        currentBoard.addMouseMotionListener(this);
        currentBoard.addMouseListener(this);
    }

    public void gameRestart() {
        mainGame.remove(currentBoard);
        initNewField(currentBoardSize);
        mainGame.add(currentBoard);
        updateLabel();
        mainGame.revalidate();
        mainGame.repaint();

//        mainGame.setFocusable(true);
//        mainGame.requestFocus();
    }
    public void checkWin(){

    }
    @Override
    public void actionPerformed(ActionEvent e) {
//        gameRestart();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currentBoardSize = ((JSlider) e.getSource()).getValue();
        gameRestart();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component current = currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Cell)) {
            return;
        }
        ((Cell) current).rotate();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                mainGame.dispose();
        }
    }
}
