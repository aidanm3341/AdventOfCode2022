package org.aidan.day1;

import org.aidan.utils.Output;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part2 implements Output<Integer> {
    @Override
    public Integer execute(List<String> input) {
        return Part2.split(input)
                .stream()
                .map(nums -> nums.stream().reduce(0, Integer::sum))
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum);
    }

    static List<List<Integer>> split(List<String> input) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();

        for(String s : input) {
            if (s.isBlank()) {
                output.add(List.copyOf(currentList));
                currentList.clear();
            } else {
                currentList.add(Integer.parseInt(s));
            }
        }

        return output;
    }
}
