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
    public static final int INITIAL_BOARD_SIZE = 8;
    private final JFrame mainGame;
    private final JLabel label;
    private GameField currentBoard;
    private int currentBoardSize;
    private int level;

    public GameLogic(JFrame mainGame, JLabel label) {
        this.mainGame = mainGame;
        this.label = label;
        level = 1;
        currentBoardSize = INITIAL_BOARD_SIZE;
        initNewField(currentBoardSize);
        mainGame.add(currentBoard);
        updateLabel();
    }

    private void updateLabel() {
        label.setText("CURRENT LEVEL: " + level + "; CURRENT BOARD SIZE: " + currentBoardSize);
        mainGame.revalidate();
        mainGame.repaint();
    }

    private void initNewField(int size) {
        currentBoard = new GameField(size);
        currentBoard.addMouseMotionListener(this);
        currentBoard.addMouseListener(this);
    }

    public void gameRestart() {
        level = 1;
        resetField();
    }

    public void checkWin() {
        if(currentBoard.isPathFromStartToFinish()){
            level++;
            resetField();
        }
    }

    private void resetField(){
        mainGame.remove(currentBoard);
        initNewField(currentBoardSize);
        mainGame.add(currentBoard);
        updateLabel();
        mainGame.revalidate();
        mainGame.repaint();

        mainGame.setFocusable(true);
        mainGame.requestFocus();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        gameRestart();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currentBoardSize = ((JSlider) e.getSource()).getValue();
        gameRestart();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        currentBoard.setDefaultColorForPipes();
        Component current = currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Cell)) {
            return;
        }
        ((Cell) current).setHighlight(true);
        ((Cell) current).rotate();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = currentBoard.getComponentAt(e.getPoint());
        if (!(current instanceof Cell)) {
            return;
        }
        ((Cell) current).setHighlight(true);
        currentBoard.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                mainGame.dispose();
                break;
            case KeyEvent.VK_ENTER:
                checkWin();
        }
    }
}
