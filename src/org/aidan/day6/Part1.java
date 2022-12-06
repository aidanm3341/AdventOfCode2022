package org.aidan.day6;

import org.aidan.utils.Output;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Part1 implements Output<Long> {
    private static final int UNIQUE_CHARS = 4;
    @Override
    public Long execute(List<String> input) {
        List<Integer> charsSoFar = new ArrayList<>();
        input.get(0).chars()
                .limit(UNIQUE_CHARS)
                .forEach(charsSoFar::add);

        return input.get(0).chars()
                .skip(UNIQUE_CHARS)
                .takeWhile(c -> containsDuplicates(charsSoFar))
                .peek(charsSoFar::add)
                .peek(c -> charsSoFar.remove(0))
                .count()
                + UNIQUE_CHARS;
    }

    private <T> boolean containsDuplicates(List<T> cs) {
        Set<T> chars = new HashSet<>(cs);
        return chars.size() < cs.size();
    }
}
