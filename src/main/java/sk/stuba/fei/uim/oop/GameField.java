package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    private int fieldSize;

    public GameField() {
        setLayout(new GridLayout(8, 8));
    }
}
