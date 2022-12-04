package org.aidan.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputReader {

    public static Stream<String> readAsStream(String src) throws FileNotFoundException {
        FileReader fr = new FileReader("resources/" + src);
        BufferedReader br = new BufferedReader(fr);
        return br.lines();
    }

    public static List<String> readAsList(String src) throws FileNotFoundException {
        FileReader fr = new FileReader("resources/" + src);
        BufferedReader br = new BufferedReader(fr);
        return br.lines().collect(Collectors.toList());
    }
}
