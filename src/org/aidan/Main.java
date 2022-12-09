package org.aidan;

import org.aidan.utils.InputReader;
import org.aidan.utils.Output;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main {
    private final List<Output<?>> parts;

    private Main() {
        parts = new ArrayList<>();
        parts.add(new org.aidan.day1.Part1());
        parts.add(new org.aidan.day1.Part2());
        parts.add(new org.aidan.day2.Part1());
        parts.add(new org.aidan.day2.Part2());
        parts.add(new org.aidan.day3.Part1());
        parts.add(new org.aidan.day3.Part2());
        parts.add(new org.aidan.day4.Part1());
        parts.add(new org.aidan.day4.Part2());
        parts.add(new org.aidan.day5.Part1());
        parts.add(new org.aidan.day5.Part2());
        parts.add(new org.aidan.day6.Part1());
        parts.add(new org.aidan.day6.Part2());
        parts.add(new org.aidan.day7.Part1());
        parts.add(new org.aidan.day7.Part2());
        parts.add(new org.aidan.day8.Part1());
        parts.add(new org.aidan.day8.Part2());
        parts.add(new org.aidan.day9.Part1());
        parts.add(new org.aidan.day9.Part2());
    }

    public void runDay(int day) throws FileNotFoundException {
        List<String> input = InputReader.readAsList("day" + day);

        System.out.println();
        System.out.println("================================");
        System.out.println("Day " + day);
        if(day <= parts.size()) {
            System.out.println("\tPart 1");
            System.out.println("\t\t" + parts.get(day*2 - 2).execute(input));
            System.out.println();
        }
        if(day <= parts.size()/2) {
            System.out.println("\tPart 2");
            System.out.println("\t\t" + parts.get(day*2 - 1).execute(input));
            System.out.println();
        }
        System.out.println("================================");
    }


    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        int day;

        if (args.length != 0)
            day = Integer.parseInt(args[0]);
        else
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        main.runDay(day);
    }
}
