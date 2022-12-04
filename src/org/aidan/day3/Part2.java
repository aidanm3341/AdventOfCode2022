package org.aidan.day3;

import org.aidan.Output;
import org.aidan.utils.Tuple3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Part2 implements Output<Integer> {
    @Override
    public Integer execute(List<String> input) {
        Tuple3<String, String, String> strings = new Tuple3<>();
        return input.stream()
                .flatMap(s -> addItemToTuple3(strings, s))
                .map(this::commonChar)
                .map(this::priority)
                .reduce(Integer::sum)
                .orElse(-1);
    }

    private Stream<Tuple3<String, String, String>> addItemToTuple3(Tuple3<String, String, String> tuple, String item) {
        if(tuple.getOne() == null) {
            tuple.setOne(item);
            return Stream.empty();
        } else if(tuple.getTwo() == null) {
            tuple.setTwo(item);
            return Stream.empty();
        } else if(tuple.getThree() == null) {
            tuple.setThree(item);
            Tuple3<String, String, String> toReturn = new Tuple3<>(tuple.getOne(), tuple.getTwo(), tuple.getThree());
            tuple.clear();
            return Stream.of(toReturn);
        }
        throw new RuntimeException("Shouldn't reach here...");
    }

    private int priority(char c) {
        if (c > 64 && c < 91)
            return c - 38;
        else if (c > 96 && c < 123)
            return c - 96;
        return -1;
    }

    private char commonChar(Tuple3<String, String, String> strings) {
        String a = strings.getOne();
        String b = strings.getTwo();
        String c = strings.getThree();

        for (char ch : a.toCharArray()) {
            if (b.contains(String.valueOf(ch)))
                if (c.contains(String.valueOf(ch)))
                    return ch;
        }
        return '-';
    }
}
