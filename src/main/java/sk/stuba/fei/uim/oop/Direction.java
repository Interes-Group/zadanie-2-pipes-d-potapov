package sk.stuba.fei.uim.oop;

public enum Direction {
    UP, RIGHT, DOWN, LEFT;

    public Direction next() {
        return values()[(ordinal() + 1) % values().length];
    }
}