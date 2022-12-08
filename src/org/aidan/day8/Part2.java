package org.aidan.day8;

import org.aidan.utils.InfiniteGrid;
import org.aidan.utils.Output;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 implements Output<Integer>  {
    private InfiniteGrid<Integer> trees;
    @Override
    public Integer execute(List<String> input) {
        trees = new InfiniteGrid<>();

        AtomicInteger row = new AtomicInteger();

        input.stream()
                .peek(line -> addTreesToRow(line, row.get()))
                .forEach(i -> row.getAndIncrement());

        int max = 0;
        for (int i = 0; i < trees.getWidth(); i++) {
            for (int j = 0; j < trees.getHeight(); j++) {
                if (getScenicScore(i, j) > max)
                    max = getScenicScore(i, j);
            }
        }
        return max;
    }

    private int getScenicScore(int x, int y) {
        return getScenicScoreUp(x, y)
                * getScenicScoreDown(x, y)
                * getScenicScoreLeft(x, y)
                * getScenicScoreRight(x, y);
    }

    private int getScenicScoreUp(int x, int y) {
        int count = 0;
        for (int i = y-1; i >= 0; i--) {
            if (trees.getElementAt(x, i) < trees.getElementAt(x, y))
                count++;
            else
                return count + 1;
        }
        return count;
    }

    private int getScenicScoreDown(int x, int y) {
        int count = 0;
        for (int i = y+1; i < trees.getHeight(); i++) {
            if (trees.getElementAt(x, i) < trees.getElementAt(x, y))
                count++;
            else
                return count + 1;
        }
        return count;
    }

    private int getScenicScoreLeft(int x, int y) {
        int count = 0;
        for (int i = x-1; i >= 0; i--) {
            if (trees.getElementAt(i, y) < trees.getElementAt(x, y))
                count++;
            else
                return count + 1;
        }
        return count;
    }

    private int getScenicScoreRight(int x, int y) {
        int count = 0;
        for (int i = x+1; i < trees.getWidth(); i++) {
            if (trees.getElementAt(i, y) < trees.getElementAt(x, y))
                count++;
            else
                return count + 1;
        }
        return count;
    }

    private void addTreesToRow(String line, int row) {
        AtomicInteger x = new AtomicInteger();
        line.chars()
                .map(i -> Integer.parseInt(String.valueOf((char) i)))
                .peek(i -> trees.set(i, x.get(), row))
                .forEach(i -> x.getAndIncrement());
    }
}
