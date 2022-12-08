package org.aidan.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InfiniteGrid<T> implements Iterable<T> {
    private final LinkedList<LinkedList<T>> grid;

    private int xOffset, yOffset;
    private final T defaultElement;

    public InfiniteGrid(T defaultElement){
        grid = new LinkedList<>();
        this.defaultElement = defaultElement;

        LinkedList<T> firstElement = new LinkedList<>();
        firstElement.add(defaultElement);
        grid.add(firstElement);

        xOffset = 0;
        yOffset = 0;
    }

    public InfiniteGrid(){
        this(null);
    }

    public void set(T element, int x, int y){
        if(x < -xOffset){
            padRows(Math.abs(xOffset + x), true);
            xOffset = -x;
        }
        if(y < -yOffset){
            padColumns(Math.abs(yOffset + y), true);
            yOffset = -y;
        }
        if(x > grid.get(0).size()-1 - xOffset){
            padRows(x - (grid.get(0).size()-1 - xOffset), false);
        }
        if(y > grid.size()-1 - yOffset){
            padColumns(y - (grid.size()-1 - yOffset), false);
        }
        grid.get(y + yOffset).set(x + xOffset, element);
    }

    private void padRows(int amount, boolean atStart){
        for (LinkedList<T> ts : grid)
            for (int j = 0; j < amount; j++)
                if(atStart)
                    ts.addFirst( defaultElement);
                else
                    ts.addLast( defaultElement);
    }

    private void padColumns(int amount, boolean atStart){
        for (int i = 0; i < amount; i++) {
            LinkedList<T> list = new LinkedList<>();
            for (int j = 0; j < grid.get(0).size(); j++)
                list.add(defaultElement);
            if(atStart)
                grid.addFirst(list);
            else
                grid.addLast(list);
        }
    }

    public T getElementAt(int x, int y){
        if(x + xOffset >= grid.get(0).size() || y + yOffset >= grid.size() ||
                x + xOffset < 0 || y + yOffset < 0)
            return defaultElement;
        else
            return grid.get(y + yOffset).get(x + xOffset);
    }

    public Tuple2<Integer, Integer> findFirst(T t){
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if(getElementAt(i, j).equals(t))
                    return new Tuple2<>(i, j);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for(List<T> ts : grid){
            output.append(ts.stream().map(t -> {
                if(t == null)
                    return ".";
                else
                    return t.toString();
            }).reduce((acc, str) -> acc + " " + str).orElse(""));
            output.append("\n");
        }
        return output.toString();
    }

    public int getWidth(){
        return grid.get(0).size();
    }

    public int getHeight(){
        return grid.size();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < grid.size() * grid.get(0).size();
            }

            @Override
            public T next() {
                int x = currentIndex % grid.get(0).size();
                int y = currentIndex / grid.get(0).size();
                currentIndex++;
                return grid.get(y).get(x);
            }
        };
    }
}
