package topic.java.learning.functional_programming;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class LambdaExample {

    public static void main(String[] args) {
        BinaryOperator<Integer> res = (a, b) -> a * b;
        System.out.println("Result of multiplication: " + res.apply(5, 10));


        List<Integer> numbers = Arrays.asList(1, 2, 3);

        int output = numbers.stream().reduce(0, Integer::sum);

        System.out.println(output);


        List<String> names = Arrays.asList("Hari", "Om", "Namo", "Bhagwate", "Vasudevaye");

        String concatenated = names.stream()
                .reduce("", (partialString, element) -> partialString + element +" ");

        System.out.println(concatenated);


        //names.forEach(System.out::println);


        String con = names.stream().filter((name) -> name.length() > 5 ).reduce("", (a, b) -> a + b);

        System.out.println(con);


        List<Integer> nums = Arrays.asList(3, 4, 6, 7, 9, 13, 12);

        nums.stream().filter(num -> num % 2 == 1).forEach(System.out::println);

        names.stream().map(String::length);











    }
}
