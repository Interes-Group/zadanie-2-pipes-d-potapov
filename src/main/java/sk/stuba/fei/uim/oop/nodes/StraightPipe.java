package sk.stuba.fei.uim.oop.nodes;

import sk.stuba.fei.uim.oop.Direction;

import java.util.Random;

public class StraightPipe extends Node{
    private Direction direction;
    private Random random;

    public StraightPipe(int x, int y) {
        super(x, y);
        random = new Random();
        this.direction = Direction.values()[random.nextInt(Direction.values().length)];
    }

    public void rotate() {
        direction = direction.next();
    }
}
