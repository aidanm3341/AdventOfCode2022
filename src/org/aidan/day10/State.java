package org.aidan.day10;

public class State {
    private int cycleCount;
    private int xRegister;

    public State() {
        xRegister = 1;
        cycleCount = 1;
    }

    public int getxRegister() {
        return xRegister;
    }

    public void setxRegister(int xRegister) {
        this.xRegister = xRegister;
    }

    public int getCycleCount() {
        return cycleCount;
    }

    public void incrementCycleCount() {
        cycleCount++;
    }

    @Override
    public String toString() {
        return "State{" +
                "cycleCount=" + cycleCount +
                ", xRegister=" + xRegister +
                '}';
    }
}
