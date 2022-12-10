package org.aidan.day8;

import org.aidan.utils.AocUtilities;
import org.aidan.utils.InfiniteGrid;
import org.aidan.utils.Output;

import java.util.List;

public class Part1 implements Output<Long>  {
    private InfiniteGrid<Integer> trees;

    @Override
    public Long execute(List<String> input) {
        trees = new InfiniteGrid<>();

        for (int i = 0; i < input.size(); i++)
            addTreesToRow(input.get(i), i);

        return AocUtilities.allCombinations(trees.getWidth(), trees.getHeight())
                .filter(coords -> isVisible(coords.getOne(), coords.getTwo()))
                .count();
    }

    private void addTreesToRow(String line, int row) {
        for (int x = 0; x < line.length(); x++) {
            int treeHeight = Character.getNumericValue(line.charAt(x));
            trees.set(treeHeight, x, row);
        }
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
}
