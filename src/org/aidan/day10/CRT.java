package org.aidan.day10;

import org.aidan.utils.InfiniteGrid;

public class CRT {
    private static final int WIDTH=40, HEIGHT=6;
    private static final char OFF=' ', ON='\u2588';

    private final InfiniteGrid<Character> screen;

    public CRT() {
        screen = new InfiniteGrid<>(OFF);
    }
    public void tick(State state) {
        int xPos = getX(state.getCycleCount());
        int yPos = getY(state.getCycleCount());
        int xRegister = state.getxRegister();

        if (xPos >= xRegister - 1 && xPos <= xRegister + 1)
            screen.set(ON, xPos, yPos);
    }

    private int getX(int cycle) {
        return (cycle - 1) % WIDTH;
    }

    private int getY(int cycle) {
        return (cycle - 1) / WIDTH;
    }

    public String toString() {
        return screen.toString();
    }
}
