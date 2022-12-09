package org.aidan.day9;

import org.aidan.utils.InfiniteGrid;
import org.aidan.utils.Output;
import org.aidan.utils.Tuple2;

import java.util.List;

public class Part1 implements Output<Integer> {

    private InfiniteGrid<Character> grid;
    private Tuple2<Integer, Integer> headPos, tailPos;

    @Override
    public Integer execute(List<String> input) {
        grid = new InfiniteGrid<>('.');
        headPos = new Tuple2<>(0, 0);
        tailPos = new Tuple2<>(0, 0);

        input.forEach(this::handleInput);

        int total = 0;
        for (Character c : grid) {
            if (c.equals('#'))
                total++;
        }
        return total;
    }

    private void handleInput(String input) {
        String[] strings = input.split(" ");
        for (int i = 0; i < Integer.parseInt(strings[1]); i++) {
            movePos(headPos, input.charAt(0));
            moveFirstTowardsSecond(tailPos, headPos);
            grid.set('#', tailPos.getOne(), tailPos.getTwo());
        }
//        draw(input);
    }

    private void draw(String input) {
        System.out.println("== " + input + " ==\n");
        char currentTailUnder = grid.getElementAt(tailPos.getOne(), tailPos.getTwo());
        grid.set('T', tailPos.getOne(), tailPos.getTwo());
        char currentHeadUnder = grid.getElementAt(headPos.getOne(), headPos.getTwo());
        grid.set('H', headPos.getOne(), headPos.getTwo());
        System.out.println(grid);
        System.out.println();
        grid.set(currentTailUnder, tailPos.getOne(), tailPos.getTwo());
        grid.set(currentHeadUnder, headPos.getOne(), headPos.getTwo());
    }

    private void movePos(Tuple2<Integer, Integer> pos, char dir) {
        switch (dir) {
            case 'U' -> pos.setTwo(pos.getTwo() + 1);
            case 'D' -> pos.setTwo(pos.getTwo() - 1);
            case 'L' -> pos.setOne(pos.getOne() - 1);
            case 'R' -> pos.setOne(pos.getOne() + 1);
        }
    }

    private void moveFirstTowardsSecond(Tuple2<Integer, Integer> tail, Tuple2<Integer, Integer> head) {
        if (Math.abs(tail.getOne() - head.getOne()) <= 1 && Math.abs(tail.getTwo() - head.getTwo()) <= 1) {
            // is adjacent
            return;
        }
        tail.setOne(tail.getOne() + Integer.signum(head.getOne() - tail.getOne()));
        tail.setTwo(tail.getTwo() + Integer.signum(head.getTwo() - tail.getTwo()));
    }
}
