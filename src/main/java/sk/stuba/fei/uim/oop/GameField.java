package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cells.Cell;
import sk.stuba.fei.uim.oop.cells.StraightPipe;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {

    public GameField(int fieldSize) {
        initField(fieldSize);
    }

    private void initField(int fieldSize){
        setLayout(new GridLayout(fieldSize, fieldSize));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                this.add(new StraightPipe());
            }
        }

    }
}
