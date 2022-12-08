package org.aidan.day8;

import org.aidan.utils.InfiniteGrid;
import org.aidan.utils.Output;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1 implements Output<Integer>  {
    private InfiniteGrid<Integer> trees;
    @Override
    public Integer execute(List<String> input) {
        trees = new InfiniteGrid<>();

        AtomicInteger row = new AtomicInteger();

        input.stream()
                .peek(line -> addTreesToRow(line, row.get()))
                .forEach(i -> row.getAndIncrement());

        int total = 0;
        for (int i = 0; i < trees.getWidth(); i++) {
            for (int j = 0; j < trees.getHeight(); j++) {
                if (isVisible(i, j))
                    total++;
            }
        }
        return total;
    }

    private boolean isVisible(int x, int y) {
        return isVisibleUp(x, y)
                || isVisibleDown(x, y)
                || isVisibleLeft(x, y)
                || isVisibleRight(x, y);
    }

    private boolean isVisibleUp(int x, int y) {
        for (int i = y-1; i >= 0; i--)
            if (trees.getElementAt(x, i) >= trees.getElementAt(x, y))
                return false;
        return true;
    }

    private boolean isVisibleDown(int x, int y) {
        for (int i = y+1; i < trees.getHeight(); i++)
            if (trees.getElementAt(x, i) >= trees.getElementAt(x, y))
                return false;
        return true;
    }

    private boolean isVisibleLeft(int x, int y) {
        for (int i = x-1; i >= 0; i--)
            if (trees.getElementAt(i, y) >= trees.getElementAt(x, y))
                return false;
        return true;
    }

    private boolean isVisibleRight(int x, int y) {
        for (int i = x+1; i < trees.getWidth(); i++)
            if (trees.getElementAt(i, y) >= trees.getElementAt(x, y))
                return false;
        return true;
    }

    private void addTreesToRow(String line, int row) {
        AtomicInteger x = new AtomicInteger();
        line.chars()
                .map(i -> Integer.parseInt(String.valueOf((char) i)))
                .peek(i -> trees.set(i, x.get(), row))
                .forEach(i -> x.getAndIncrement());
    }
}
