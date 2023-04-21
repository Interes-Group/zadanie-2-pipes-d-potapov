package sk.stuba.fei.uim.oop.controls;

import sk.stuba.fei.uim.oop.field.GameField;
import sk.stuba.fei.uim.oop.cells.Cell;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameLogic extends UniversalAdapter {
    public static final int INITIAL_FIELD_SIZE = 8;
    private final JFrame gameFrame;
    private final JLabel label;
    private GameField field;
    private int fieldSize;
    private int level;

    public GameLogic(JFrame gameFrame, JLabel label) {
        this.gameFrame = gameFrame;
        this.label = label;
        fieldSize = INITIAL_FIELD_SIZE;
        level = 1;

        initNewField();
        gameFrame.add(field);

        updateLabel();
    }

    private void initNewField() {
        field = new GameField(fieldSize);
        field.addMouseMotionListener(this);
        field.addMouseListener(this);
    }

    private void updateLabel() {
        label.setText("CURRENT LEVEL: " + level + "; CURRENT BOARD SIZE: " + fieldSize);
        label.repaint();
    }

    public void gameRestart() {
        level = 1;
        resetField();
    }

    public void checkWin() {
        if (field.isPathFromStartToFinish()) {
            level++;
            resetField();
        }
    }

    private void resetField() {
        gameFrame.remove(field);
        initNewField();
        gameFrame.add(field);
        updateLabel();
        gameFrame.revalidate();
        gameFrame.repaint();

        gameFrame.setFocusable(true);
        gameFrame.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameRestart();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
            fieldSize = ((JSlider) e.getSource()).getValue();
            gameRestart();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        field.setDefaultColorForPipes();
        Component current = field.getComponentAt(e.getX(), e.getY());
        if (!(current instanceof Cell)) {
            return;
        }
        ((Cell) current).setHighlight(true);
        ((Cell) current).rotate();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component current = field.getComponentAt(e.getPoint());
        if (!(current instanceof Cell)) {
            field.repaint();
            return;
        }
        ((Cell) current).setHighlight(true);
        field.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        field.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                gameRestart();
                break;
            case KeyEvent.VK_ESCAPE:
                gameFrame.dispose();
                System.exit(0);
                break;
            case KeyEvent.VK_ENTER:
                checkWin();
        }
    }
}
