package org.aidan.day11;

import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Predicate;

public class MonkeyPart1 {
    private final Queue<Integer> items;
    private final Function<Integer, Integer> operation;
    private final Predicate<Integer> test;
    private int monkeyOnTrue, monkeyOnFalse;
    private int numOfInspections;

    public MonkeyPart1(Queue<Integer> initialItems, Function<Integer, Integer> operation, Predicate<Integer> test, int monkeyOnTrue, int monkeyOnFalse) {
        this.items = initialItems;
        this.operation = operation;
        this.test = test;
        this.monkeyOnTrue = monkeyOnTrue;
        this.monkeyOnFalse = monkeyOnFalse;
    }

    public void takeTurn(List<MonkeyPart1> monkeys) {
        while (!items.isEmpty()) {
            inspectNext(monkeys);
        }
    }

    private void inspectNext(List<MonkeyPart1> monkeys) {
        int newWorryLevel = Math.floorDiv(operation.apply(items.poll()), 3);
        if (test.test(newWorryLevel))
            monkeys.get(monkeyOnTrue).receiveItem(newWorryLevel);
        else
            monkeys.get(monkeyOnFalse).receiveItem(newWorryLevel);

        numOfInspections++;
    }

    public void receiveItem(int item) {
        items.add(item);
    }

    public int getNumOfInspections() {
        return numOfInspections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonkeyPart1 monkey = (MonkeyPart1) o;
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
