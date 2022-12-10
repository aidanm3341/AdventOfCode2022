package org.aidan.day10;

import org.aidan.utils.Output;

import java.util.List;
import java.util.stream.Stream;

public class Part1 implements Output<Integer>  {

    private State state;
    @Override
    public Integer execute(List<String> input) {
        state = new State();

        return input.stream()
                .flatMap(this::parseInstruction)
                .peek(i -> i.tick(state))
                .filter(i -> interestingCycle())
                .map(i -> signalStrength())
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private boolean interestingCycle() {
        return state.getCycleCount() == 20 || (state.getCycleCount() - 20) % 40 == 0;
    }

    private int signalStrength() {
        return state.getCycleCount() * state.getxRegister();
    }

    private Stream<Instruction> parseInstruction(String input) {
        String[] parts = input.split(" ");

        return switch (parts[0]) {
            case "noop" -> Stream.of(new Instruction.Noop());
            case "addx" -> Stream.of(
                    new Instruction.Noop(),
                    new Instruction.AddX(Integer.parseInt(parts[1]))
            );
            default -> throw new RuntimeException("Invalid instruction: " + parts[0]);
        };
    }
}
