package org.aidan.day9;

import org.aidan.utils.InfiniteGrid;
import org.aidan.utils.Output;
import org.aidan.utils.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class Part2 implements Output<Long> {

    private InfiniteGrid<Character> grid;
    private List<Tuple2<Integer, Integer>> poses;

    @Override
    public Long execute(List<String> input) {
        grid = new InfiniteGrid<>('.');
        poses = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            poses.add(new Tuple2<>(0, 0));

        input.forEach(this::handleInput);
        return grid.stream()
                .filter(c -> c.equals('#'))
                .count();
    }

    private void handleInput(String input) {
        char direction = input.charAt(0);
        int amount = Integer.parseInt(input.substring(2));

        for (int i = 0; i < amount; i++) {
            movePos(poses.get(0), direction);

            for (int j = 0; j < poses.size()-1; j++)
                moveFirstTowardsSecond(poses.get(j+1), poses.get(j));

            grid.set('#', poses.get(9).getOne(), poses.get(9).getTwo());
        }
    }

    private void movePos(Tuple2<Integer, Integer> pos, char dir) {
        switch (dir) {
            case 'U' -> pos.setTwo(pos.getTwo() - 1);
            case 'D' -> pos.setTwo(pos.getTwo() + 1);
            case 'L' -> pos.setOne(pos.getOne() - 1);
            case 'R' -> pos.setOne(pos.getOne() + 1);
        }
    }

    private void moveFirstTowardsSecond(Tuple2<Integer, Integer> tail, Tuple2<Integer, Integer> head) {
        boolean isAdjacent = Math.abs(tail.getOne() - head.getOne()) <= 1 && Math.abs(tail.getTwo() - head.getTwo()) <= 1;

        if (!isAdjacent) {
            tail.setOne(tail.getOne() + Integer.signum(head.getOne() - tail.getOne()));
            tail.setTwo(tail.getTwo() + Integer.signum(head.getTwo() - tail.getTwo()));
        }
    }
}

