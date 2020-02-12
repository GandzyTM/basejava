package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStreamTest {
    public static void main(String[] args) {
        int[] values = {9, 8};
        System.out.println(minValues(values));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 6, 5, 4, 3, 2, 5, 4, 5, 5)));
    }

    public static int minValues(int[] values) {
        return Integer.parseInt(Arrays.stream(values).sorted().distinct().mapToObj(String::valueOf).collect(Collectors.joining()));
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream().filter(x -> (x % 2) == (integers.stream().reduce(0, Integer::sum) % 2)).distinct().collect(Collectors.toList());
    }
}