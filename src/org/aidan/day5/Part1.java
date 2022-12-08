package org.aidan.day5;

import org.aidan.utils.Output;
import org.aidan.utils.Tuple3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 implements Output<String> {
    private List<Deque<Character>> stacks;

    public Part1() {
        stacks = new ArrayList<>();
    }

    @Override
    public String execute(List<String> input) {
        int linesToSkip = initialiseStacks(input);
        input.stream()
                .skip(linesToSkip+1)
                .map(this::parseInstruction)
                .forEach(this::moveXFromStackToStack);

        return stacks.stream()
                .map(Deque::peek)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void moveXFromStackToStack(Tuple3<Integer, Integer, Integer> tuple) {
        int numberToTake = tuple.getOne();
        Deque<Character> drain = stacks.get(tuple.getTwo() - 1);
        Deque<Character> sink = stacks.get(tuple.getThree() - 1);

        Stream.generate(() -> 0)
                .limit(numberToTake)
                .forEach(i -> sink.push(drain.pop()));
    }

    private Tuple3<Integer, Integer, Integer> parseInstruction(String s) {
        String[] segments = s.split(" ");

        return new Tuple3<>(
                Integer.parseInt(segments[1]),
                Integer.parseInt(segments[3]),
                Integer.parseInt(segments[5])
        );
    }

    private int initialiseStacks(List<String> input) {
        List<String> setupStacks = input.stream()
                .takeWhile(s -> !s.equals(""))
                .toList();
        int numberOfStacks = setupStacks.get(setupStacks.size() - 1).split("   ").length;

//        stacks = List.of(new ArrayDeque<>());
//        stacks = Stream.generate(ArrayDeque::new).limit(numberOfStacks).toList();
//        Stream.generate(ArrayDeque::new).limit(numberOfStacks).toList();
        for (int i = 0; i < numberOfStacks; i++)
            stacks.add(new ArrayDeque<>());

        for (int j = setupStacks.size() - 2; j >= 0; j--) {
            for (int i = 0; i < numberOfStacks; i++) {
                char c = (i * 4) + 1 < setupStacks.get(j).length()
                        ? setupStacks.get(j).charAt((i * 4) + 1)
                        : ' ';

                if (c != ' ')
                    stacks.get(i).push(c);
            }
        }
        return setupStacks.size();
    }
}
