package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cells.ArchedPipe;
import sk.stuba.fei.uim.oop.cells.Cell;
import sk.stuba.fei.uim.oop.cells.StraightPipe;
import sk.stuba.fei.uim.oop.cells.outermostPipe;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameField extends JPanel {
    private Random random;
    private int fieldSize;

    public GameField(int fieldSize) {
        setLayout(new GridLayout(fieldSize, fieldSize));
        this.fieldSize = fieldSize;
        random = new Random();
        initField();
    }

    private void initField(){
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (i == 0)
                    add(new StraightPipe());
                else if (i == 2)
                    add(new ArchedPipe());
                else add(new Cell());
            }
        }

    }
}
