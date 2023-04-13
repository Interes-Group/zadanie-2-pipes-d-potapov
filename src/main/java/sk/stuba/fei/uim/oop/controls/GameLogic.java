package sk.stuba.fei.uim.oop.controls;

import sk.stuba.fei.uim.oop.GameField;
import sk.stuba.fei.uim.oop.cells.StraightPipe;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_BOARD_SIZE = 6;
    private JFrame mainGame;
    private GameField currentBoard;
    private JLabel label;
    private int currentBoardSize;

    public GameLogic(JFrame mainGame) {
        this.mainGame = mainGame;
        currentBoardSize = INITIAL_BOARD_SIZE;
        initNewField(currentBoardSize);
        mainGame.add(currentBoard);
        label = new JLabel();
        updateNameLabel();
    }
    private void updateNameLabel() {
        label.setText("BABABABA CURRENT BOARD SIZE: " + currentBoardSize);
        mainGame.revalidate();
        mainGame.repaint();
    }

    private void gameRestart() {
        mainGame.remove(currentBoard);
        initNewField(currentBoardSize);
        mainGame.add(currentBoard);
        updateNameLabel();
    }

    private void initNewField(int size){
        currentBoard = new GameField(size);
        currentBoard.addMouseMotionListener(this);
        currentBoard.addMouseListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameRestart();
        this.mainGame.revalidate();
        this.mainGame.repaint();
        this.mainGame.setFocusable(true);
        this.mainGame.requestFocus();
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Component current = currentBoard.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof StraightPipe)) {
            return;
        }
        ((StraightPipe) current).rotate();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currentBoardSize = ((JSlider) e.getSource()).getValue();
//        restartGame();
//        gameFrame.setFocusable(true);
//        gameFrame.requestFocus();

        updateNameLabel();
        gameRestart();
        mainGame.setFocusable(true);
        mainGame.requestFocus();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                this.gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                this.mainGame.dispose();
        }
    }

    public JLabel getLabel() {
        return label;
    }
}
