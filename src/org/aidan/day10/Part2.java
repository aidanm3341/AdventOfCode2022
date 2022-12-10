package org.aidan.day10;

import org.aidan.utils.Output;

import java.util.List;
import java.util.stream.Stream;

public class Part2 implements Output<String>  {

    private State state;
    @Override
    public String execute(List<String> input) {
        state = new State();
        CRT crt = new CRT();

        input.stream()
                .flatMap(this::parseInstruction)
                .peek(i -> crt.tick(state))
                .forEach(i -> i.tick(state));

        return crt.toString();
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
