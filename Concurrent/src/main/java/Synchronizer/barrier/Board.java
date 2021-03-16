package Synchronizer.barrier;

public class Board {
    private int maxX;
    private int maxY;
    private int computeValue;

    public void commitNewValues() {

    }

    public boolean hasConverged() {
        return true;
    }

    public Board getSubBoard(int count, int i) {
        return new Board();
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setNewValue(int x, int y, int computeValue) {
        this.maxX = x;
        this.maxY = y;
        this.computeValue = computeValue;
    }

    public void waitForConvergence() {
    }
}
