package org.aidan.day4;

import org.aidan.utils.Output;
import org.aidan.utils.Tuple2;

import java.util.List;

public class Part2 implements Output<Long> {
    @Override
    public Long execute(List<String> input) {
        return input.stream()
                .map(s -> s.split(","))
                .map(pairs -> new Tuple2<>(
                        new Range(pairs[0]),
                        new Range(pairs[1])
                ))
                .filter(ranges -> ranges.getOne().overlaps(ranges.getTwo()) || ranges.getTwo().overlaps(ranges.getOne()))
                .count();
    }

    static class Range {
        public int lower, upper;

        public Range(String s) {
            String[] strings = s.split("-");
            this.lower = Integer.parseInt(strings[0]);
            this.upper = Integer.parseInt(strings[1]);
        }

        public boolean overlaps(Range range) {
            return range.upper >= this.lower && range.lower <= this.upper;
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
