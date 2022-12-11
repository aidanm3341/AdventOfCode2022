package org.aidan.day11;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;

public class MonkeyPart2 {
    private final Queue<BigInteger> items;
    private final Function<BigInteger, BigInteger> operation;
    private final Predicate<BigInteger> test;
    private int monkeyOnTrue, monkeyOnFalse;
    private long numOfInspections;
    private int divisor;

    public MonkeyPart2(Queue<BigInteger> initialItems, Function<BigInteger, BigInteger> operation, Predicate<BigInteger> test, int monkeyOnTrue, int monkeyOnFalse, int divisor) {
        this.items = initialItems;
        this.operation = operation;
        this.test = test;
        this.monkeyOnTrue = monkeyOnTrue;
        this.monkeyOnFalse = monkeyOnFalse;
        this.divisor = divisor;
    }

    public void takeTurn(List<MonkeyPart2> monkeys, int divisor) {
        while (!items.isEmpty()) {
            inspectNext(monkeys, divisor);
        }
    }

    private void inspectNext(List<MonkeyPart2> monkeys, int divisor) {
        BigInteger newWorryLevel = operation.apply(items.poll());
        newWorryLevel = newWorryLevel.mod(BigInteger.valueOf(divisor));

        if (test.test(newWorryLevel))
            monkeys.get(monkeyOnTrue).receiveItem(newWorryLevel);
        else
            monkeys.get(monkeyOnFalse).receiveItem(newWorryLevel);

        numOfInspections++;
    }

    public void receiveItem(BigInteger item) {
        items.add(item);
    }

    public long getNumOfInspections() {
        return numOfInspections;
    }

    public int getDivisor() {
        return divisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonkeyPart2 monkey = (MonkeyPart2) o;
        return monkeyOnTrue == monkey.monkeyOnTrue && monkeyOnFalse == monkey.monkeyOnFalse && numOfInspections == monkey.numOfInspections && Objects.equals(items, monkey.items) && Objects.equals(operation, monkey.operation) && Objects.equals(test, monkey.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, operation, test, monkeyOnTrue, monkeyOnFalse, numOfInspections);
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                ", operation=" + operation +
                ", test=" + test +
                ", monkeyOnTrue=" + monkeyOnTrue +
                ", monkeyOnFalse=" + monkeyOnFalse +
                ", numOfInspections=" + numOfInspections +
                '}';
    }
}
