package org.aidan.day3;

import org.aidan.utils.Output;

import java.util.List;

public class Part1 implements Output<Integer> {
    @Override
    public Integer execute(List<String> input) {
        return input.stream()
                .map(Rucksack::new)
                .map(rucksack -> commonChar(rucksack.getComp1(), rucksack.getComp2()))
                .map(this::priority)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private int priority(char c) {
        if (c > 64 && c < 91)
            return c - 38;
        else if (c > 96 && c < 123)
            return c - 96;
        return -1;
    }

    private char commonChar(String a, String b) {
        for (char c : a.toCharArray()) {
            if (b.contains(String.valueOf(c)))
                return c;
        }
        return '-';
    }

    static class Rucksack {
        private final String comp1, comp2;

        public Rucksack(String contents) {
            this.comp1 = contents.substring(0, contents.length()/2);
            this.comp2 = contents.substring(contents.length()/2);
        }

        public String getComp1() {
            return comp1;
        }

        public String getComp2() {
            return comp2;
        }
    }
}
