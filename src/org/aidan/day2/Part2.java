package org.aidan.day2;

import org.aidan.Output;
import java.util.List;

public class Part2 implements Output<Integer> {
    @Override
    public Integer execute(List<String> input) {
        return input.stream()
                .map(s -> Part2.score(s.charAt(0), s.charAt(2)))
                .reduce(0, Integer::sum);
    }

    static int score(char a, char b) {
        int desiredResult = calcResult(b);
        Move elf = Move.fromChar(a);
        Move me = calcMove(elf, desiredResult);

        return ((me.compare(elf) + 1) * 3) + me.ordinal() + 1;
    }

    static int calcResult(char c) {
        return switch(c) {
            case 'X' -> -1;
            case 'Y' -> 0;
            case 'Z' -> 1;
            default -> throw new RuntimeException("Invalid result character");
        };
    }

    static Move calcMove(Move elf, int result) {
        for (Move m : Move.values())
            if (m.compare(elf) == result)
                return m;
        return elf; // never reached
    }

    enum Move {
        ROCK, PAPER, SCISSORS;

        static Move fromChar(char c) {
            return switch(c){
                case 'A' -> ROCK;
                case 'B' -> PAPER;
                case 'C' -> SCISSORS;
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
