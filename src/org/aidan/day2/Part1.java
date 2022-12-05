package org.aidan.day2;

import org.aidan.utils.Output;

import java.util.List;

public class Part1 implements Output<Integer> {
    @Override
    public Integer execute(List<String> input) {
        return input.stream()
                .map(s -> Part1.score(
                        Move.fromChar(s.charAt(0)),
                        Move.fromChar(s.charAt(2))
                ))
                .reduce(0, Integer::sum);
    }

    static int score(Move elf, Move me) {
        return ((me.compare(elf) + 1) * 3) + me.ordinal() + 1;
    }

    enum Move {
        ROCK, PAPER, SCISSORS;

        static Move fromChar(char c) {
            return switch(c) {
                case 'A', 'X' -> ROCK;
                case 'B', 'Y' -> PAPER;
                case 'C', 'Z' -> SCISSORS;
                default -> throw new RuntimeException("Not a valid move");
            };
        }

        Move getsBeatenBy() {
            return switch(this) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                case SCISSORS -> ROCK;
            };
        }

        int compare(Move other) {
            if(this == other)
                return 0;
            else if(this.getsBeatenBy() == other)
                return -1;
            else
                return 1;
        }
    }
}
