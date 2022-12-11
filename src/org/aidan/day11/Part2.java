package org.aidan.day11;

import org.aidan.utils.Output;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Part2 implements Output<Long> {
    @Override
    public Long execute(List<String> lines) {
        List<String> input = new ArrayList<>(lines);
        List<MonkeyPart2> monkeys = new ArrayList<>();

        while(!input.isEmpty()) {
            monkeys.add(parseMonkey(input.subList(0, 7)));
            input.subList(0, 7).clear();
        }

        int divisor = 1;
        for (MonkeyPart2 monkey : monkeys)
            divisor *= monkey.getDivisor();

        for (int i = 0; i < 10000; i++) {
            for (MonkeyPart2 monkey : monkeys)
                monkey.takeTurn(monkeys, divisor);

            if (i+1 == 1 || i+1 == 20 || (i+1) % 1000 == 0) {
                System.out.println("==== Round " + (i+1) + " ======");
                for (int j = 0; j < monkeys.size(); j++) {
                    System.out.println(j + " " + monkeys.get(j).getNumOfInspections());
                }
                System.out.println();
            }
        }

        monkeys.sort(Comparator.comparing(MonkeyPart2::getNumOfInspections).reversed());
        return monkeys.stream()
                .limit(2)
                .map(MonkeyPart2::getNumOfInspections)
                .reduce((acc, x) -> x * acc)
                .orElse(-1L);
    }

    private MonkeyPart2 parseMonkey(List<String> monkeyLines) {
        Queue<BigInteger> items = parseQueue(monkeyLines.get(1).trim());
        Function<BigInteger, BigInteger> operation = parseOperation(monkeyLines.get(2).trim());
        Predicate<BigInteger> test = parseTest(monkeyLines.get(3).trim());
        int monkeyOnTrue = parseMonkeyToPassTo(monkeyLines.get(4).trim());
        int monkeyOnFalse = parseMonkeyToPassTo(monkeyLines.get(5).trim());
        int divisor = parseDivisor(monkeyLines.get(3).trim());

        return new MonkeyPart2(items, operation, test, monkeyOnTrue, monkeyOnFalse, divisor);
    }

    private Queue<BigInteger> parseQueue(String line) {
        Queue<BigInteger> items = new LinkedList<>();
        String[] nums = line.split(":")[1].split(",");

        Stream.of(nums)
                .map(num -> Integer.parseInt(num.trim()))
                .map(BigInteger::valueOf)
                .forEach(items::add);

        return items;
    }

    private Function<BigInteger, BigInteger> parseOperation(String input) {
        String[] exprComponents = input.split("=")[1].trim().split(" ");

        if (exprComponents[2].equals("old"))
            return x -> x.multiply(x);

        BigInteger val = BigInteger.valueOf(Integer.parseInt(exprComponents[2]));
        if (exprComponents[1].equals("+"))
            return x -> x.add(val);
        else if (exprComponents[1].equals("*"))
            return x -> x.multiply(val);

        throw new RuntimeException("Unable to parse operation - " + input);
    }

    private Predicate<BigInteger> parseTest(String input) {
        BigInteger divisor = BigInteger.valueOf(Integer.parseInt(input.split(" ")[3]));
        return x -> x.mod(divisor).equals(BigInteger.ZERO);
    }

    private int parseMonkeyToPassTo(String input) {
        return Integer.parseInt(input.split(" ")[5]);
    }

    private int parseDivisor(String input) {
        return Integer.parseInt(input.split(" ")[3]);
    }
}
