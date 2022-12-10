package org.aidan.utils;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AocUtilities {
    public static Stream<Tuple2<Integer, Integer>> allCombinations(int lowerX, int upperX, int lowerY, int upperY) {
        return IntStream.range(lowerX, upperX)
                .mapToObj(x ->
                        IntStream.range(lowerY, upperY)
                                .mapToObj(y -> new Tuple2<>(x, y))
                ).flatMap(s -> s);
    }

    public static Stream<Tuple2<Integer, Integer>> allCombinations(int upperX, int upperY) {
        return allCombinations(0, upperX, 0, upperY);
    }
}
