package topic.java.learning.functional_programming;

import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExcerciseFunctionalProgramming {

    public static void main(String[] args) {


        int num = 10;



        PrimeCheck primeCheck = num1 -> {
            if (num1 <= 1) return false;  // Handle 1, 0, and negatives
            if (num1 == 2) return true;   // 2 is prime
            if (num1 % 2 == 0) return false;  // Even numbers > 2 aren't prime

            for(int i = 3; i <= Math.sqrt(num1); i += 2) {  // Only check odd divisors up to √num1
                if(num1 % i == 0) {
                    return false;
                }
            }
            return true;
        };


        System.out.println(primeCheck.isPrime(10));
        System.out.println(primeCheck.isPrime(13));
        System.out.println(primeCheck.isPrime(71));


        int a = 7;

       Fact fact = x -> {
           if(x == 0 || x == 1){
               return 1;
           }

           int res = 1;

           for(int i = x; i > 1; i--){
               res *= i;
           }

           return res;
       };

        System.out.println(fact.fact(a));



        /*
         * This is a functional programming exercise to calculate the factorial of a number using IntStream.
         * The code uses IntStream to generate a range of integers from 2 to the given number (inclusive),
         * and then applies a reduction operation to multiply all the integers in that range.
         */
        // Calculate factorial using IntStream
        IntStream.rangeClosed(2, a)
                .reduce((b, c) -> b * c).ifPresent(System.out::println);


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);


        list = list.stream().distinct().collect(Collectors.toList());

        System.out.println(list);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Sumit", "Engineering", 30, 100));
        employeeList.add(new Employee("Ram", "Manager", 29, 123));
        employeeList.add(new Employee("Shyam", "Sales", 29, 893));
        employeeList.add(new Employee("GhanShyam", "HR", 29, 212));
        employeeList.add(new Employee("Aviram", "HR", 29, 323));
        employeeList.add(new Employee("Mohan", "Sales", 29, 143));

        Comparator<Employee> employeeComparator = Comparator.comparingDouble(Employee::getSalary);

        System.out.println(employeeList);

        employeeList = employeeList.stream().sorted(employeeComparator).collect(Collectors.toList());

        System.out.println(employeeList);



        List<String> numString = new ArrayList<>();

        numString.add("1");
        numString.add("2");
        numString.add("3");
        numString.add("4");


        int sumOfSquare = numString.stream()
                .map(Integer::parseInt)
                .map((x) -> x * x)
                .reduce(Integer::sum).get();

        System.out.println(sumOfSquare);












    }
}
