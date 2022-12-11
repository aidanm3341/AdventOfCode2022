package org.aidan.day11;

import org.aidan.utils.Output;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Part1 implements Output<Integer> {
    @Override
    public Integer execute(List<String> lines) {
        List<String> input = new ArrayList<>(lines);

        List<MonkeyPart1> monkeys = new ArrayList<>();
        while(!input.isEmpty()) {
            monkeys.add(parseMonkey(input.subList(0, 7)));
            input.subList(0, 7).clear();
        }

        for (int i = 0; i < 20; i++) {
            for (MonkeyPart1 monkey : monkeys)
                monkey.takeTurn(monkeys);
        }

        monkeys.sort(Comparator.comparingInt(MonkeyPart1::getNumOfInspections).reversed());
        return monkeys.stream()
                .limit(2)
                .map(MonkeyPart1::getNumOfInspections)
                .reduce((acc, x) -> acc * x)
                .orElse(-1);
    }

    private MonkeyPart1 parseMonkey(List<String> monkeyLines) {
        Queue<Integer> items = parseQueue(monkeyLines.get(1).trim());
        Function<Integer, Integer> operation = parseOperation(monkeyLines.get(2).trim());
        Predicate<Integer> test = parseTest(monkeyLines.get(3).trim());
        int monkeyOnTrue = parseMonkeyToPassTo(monkeyLines.get(4).trim());
        int monkeyOnFalse = parseMonkeyToPassTo(monkeyLines.get(5).trim());

        return new MonkeyPart1(items, operation, test, monkeyOnTrue, monkeyOnFalse);
    }

    private Queue<Integer> parseQueue(String line) {
        Queue<Integer> items = new LinkedList<>();
        String[] nums = line.split(":")[1].split(",");

        Stream.of(nums)
                .map(num -> Integer.parseInt(num.trim()))
                .forEach(items::add);

        return items;
    }

    private Function<Integer, Integer> parseOperation(String input) {
        String[] exprComponents = input.split("=")[1].trim().split(" ");

        if (exprComponents[2].equals("old"))
            return x -> x * x;

        if (exprComponents[1].equals("+"))
            return x -> x + Integer.parseInt(exprComponents[2]);
        else if (exprComponents[1].equals("*"))
            return x -> x * Integer.parseInt(exprComponents[2]);

        throw new RuntimeException("Unable to parse operation - " + input);
    }

    private Predicate<Integer> parseTest(String input) {
        int divisor = Integer.parseInt(input.split(" ")[3]);
        return x -> x % divisor == 0;
    }

    private int parseMonkeyToPassTo(String input) {
        return Integer.parseInt(input.split(" ")[5]);
    }
}
