package org.aidan.day9;

import org.aidan.utils.InfiniteGrid;
import org.aidan.utils.Output;
import org.aidan.utils.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class Part2 implements Output<Integer> {

    private InfiniteGrid<Character> grid;
    private List<Tuple2<Integer, Integer>> poses;

    @Override
    public Integer execute(List<String> input) {
        grid = new InfiniteGrid<>('.');
        poses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            poses.add(new Tuple2<>(0, 0));
        }

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
            movePos(poses.get(0), input.charAt(0));
            for (int j = 0; j < poses.size()-1; j++) {
                moveFirstTowardsSecond(poses.get(j+1), poses.get(j));
            }
            grid.set('#', poses.get(9).getOne(), poses.get(9).getTwo());
        }
        draw(input);
    }

    private void draw(String input) {
        List<Character> originalChars = new ArrayList<>();
        System.out.println("== " + input + " ==\n");

        for (Tuple2<Integer, Integer> pose : poses)
            originalChars.add(grid.getElementAt(pose.getOne(), pose.getTwo()));

        for (int i = 0; i < poses.size(); i++)
            grid.set((""+i).charAt(0), poses.get(i).getOne(), poses.get(i).getTwo());

        System.out.println(grid);
        System.out.println();

        for (int i = 0; i < poses.size(); i++)
            grid.set(originalChars.get(i), poses.get(i).getOne(), poses.get(i).getTwo());
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
        if (Math.abs(tail.getOne() - head.getOne()) <= 1 && Math.abs(tail.getTwo() - head.getTwo()) <= 1) {
            // is adjacent
            return;
        }
        tail.setOne(tail.getOne() + Integer.signum(head.getOne() - tail.getOne()));
        tail.setTwo(tail.getTwo() + Integer.signum(head.getTwo() - tail.getTwo()));
    }
}

