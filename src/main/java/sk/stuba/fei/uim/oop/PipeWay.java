package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.nodes.Node;

import java.util.List;
import java.util.Random;

public class PipeWay {
    private int size;
    private List<Node> path;
//    private matrix =
    private Random random;
    private Node startNode, finishNode;

    public PipeWay(int size){
        this.size = size;
        random = new Random();
        generateStartAndFinish();
        generatePath();
    }
    private void generateStartAndFinish(){
        Direction startSide = Direction.values()[random.nextInt(Direction.values().length)];
        switch (startSide){
            case UP:
                startNode = new Node(random.nextInt(4), 0);
                finishNode = new Node(random.nextInt(4), size - 1);
                break;
            case RIGHT:
                startNode = new Node(size - 1, random.nextInt(4));
                finishNode = new Node(0, random.nextInt(4));
                break;
            case DOWN:
                startNode = new Node(random.nextInt(4), size - 1);
                finishNode = new Node(random.nextInt(4), 0);
                break;
            case LEFT:
                startNode = new Node(0, random.nextInt(4));
                finishNode = new Node(size - 1, random.nextInt(4));
                break;
        }
    }

    public void generatePath() {
        path.add(startNode);

        if (!findPathRecursive(startNode.getX(), startNode.getY())) {
            System.err.println("Error: no path found.");
        }
    }
    private boolean findPathRecursive(int x, int y) {
        if (finishNode == startNode) {
            return true;
        }

//        if (x < 0 || x > 7 || y < 0 || y > 7 || matrix[x][y] != matrix[startX][startY]) {
//            return false;
//        }
//
//        // Mark the current point as visited
//        matrix[x][y] = -1;

        // Recursively search in all directions
        if (findPathRecursive(x + 1, y) || findPathRecursive(x - 1, y) || findPathRecursive(x, y + 1) || findPathRecursive(x, y - 1)) {
            // Add the current point to the path
//            path.add(new int[]{x, y});
            return true;
        }

        return false;
    }
}
