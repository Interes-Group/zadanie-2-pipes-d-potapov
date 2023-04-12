package sk.stuba.fei.uim.oop.nodes;

import javax.swing.*;

public class Node extends JButton {
    private final int x;
    private final int y;
    private boolean highlight;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
