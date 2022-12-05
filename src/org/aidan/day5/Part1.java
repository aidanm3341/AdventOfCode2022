package org.aidan.day5;

import org.aidan.utils.Output;
import org.aidan.utils.Tuple3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Part1 implements Output<String> {
    private final List<Stack<Character>> stacks;

    public Part1() {
        stacks = new ArrayList<>();
    }
    @Override
    public String execute(List<String> input) {
        int linesToSkip = initialiseStacks(input);
        input.stream()
                .skip(linesToSkip+1)
                .map(this::parseInstruction)
                .forEach(tuple -> moveXFromStackToStack(
                        tuple.getOne(),
                        stacks.get(tuple.getTwo()-1),
                        stacks.get(tuple.getThree()-1)
                ));

        return stacks.stream()
                .map(Stack::peek)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private void moveXFromStackToStack(int x, Stack<Character> stackDrain, Stack<Character> stackSink) {
        for (int i = 0; i < x; i++)
            stackSink.push(stackDrain.pop());
    }

    private Tuple3<Integer, Integer, Integer> parseInstruction(String s) {
        String[] strings = s.split(" ");

        return new Tuple3<>(
                Integer.parseInt(strings[1]),
                Integer.parseInt(strings[3]),
                Integer.parseInt(strings[5])
        );
    }

    private int initialiseStacks(List<String> input) {
        List<String> setupStacks = input.stream()
                .takeWhile(s -> !s.equals(""))
                .toList();
        int numberOfStacks = setupStacks.get(setupStacks.size() - 1).split("   ").length;

        for (int i = 0; i < numberOfStacks; i++)
            stacks.add(new Stack<>());

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
