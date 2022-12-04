package org.aidan.day4;

import org.aidan.Output;
import org.aidan.utils.Tuple2;

import java.util.List;
import java.util.stream.Stream;

public class Part1 implements Output<Long> {
    @Override
    public Long execute(List<String> input) {
        return input.stream()
                .map(s -> s.split(","))
                .map(pairs -> new Tuple2<>(
                        new Range(pairs[0]),
                        new Range(pairs[1])
                ))
                .filter(ranges -> ranges.getOne().contains(ranges.getTwo()) || ranges.getTwo().contains(ranges.getOne()))
                .count();
    }

    static class Range {
        public int lower, upper;

        public Range(String s) {
            String[] strings = s.split("-");
            this.lower = Integer.parseInt(strings[0]);
            this.upper = Integer.parseInt(strings[1]);
        }

        public boolean contains(Range range) {
            return range.lower >= this.lower && range.upper <= this.upper;
        }

        @Override
        public String toString() {
            return "Range{" +
                    "lower=" + lower +
                    ", upper=" + upper +
                    '}';
        }
    }
}
