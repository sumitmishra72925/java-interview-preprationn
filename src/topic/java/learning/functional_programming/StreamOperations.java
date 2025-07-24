package topic.java.learning.functional_programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamOperations {


    public static class Customer{

        private String name;
        private String email;
        private  List<String> phoneNumbers;

        public Customer(String name, String email, List<String> phoneNumbers) {
            this.name = name;
            this.email = email;
            this.phoneNumbers = phoneNumbers;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public List<String> getPhoneNumbers() {
            return phoneNumbers;
        }
    }

    public static List<Customer> getCustomerList(){
        List<Customer> customers = new ArrayList<>();

        customers.add(new Customer("Sumit", "s@gmail.com", Arrays.asList("7557585230", "98655384954")));
        customers.add(new Customer("Alice", "a@gmail.com", Arrays.asList("8102750055", "98655384987")));
        customers.add(new Customer("Bob", "sujet@gmail.com", Arrays.asList("7634528234", "98655758954")));
        customers.add(new Customer("Charlie", "v@gmail.com", Arrays.asList("81878750055", "9878984987")));
        customers.add(new Customer("Ram", "r@gmail.com", Arrays.asList("7634876234", "9887568954")));
        customers.add(new Customer("AB", "r@gmail.com", Arrays.asList("7634876234", "9887568954")));
        customers.add(new Customer("ABC", "r@gmail.com", Arrays.asList("7634876234", "9887568954")));
        customers.add(new Customer("St", "r@gmail.com", Arrays.asList("7634876234", "9887568954")));
        customers.add(new Customer("ste", "r@gmail.com", Arrays.asList("7634876234", "9887568954")));

        return customers;
    }

    public static void main(String[] args) {
        List<Customer> customers = getCustomerList();

        //List<Customer> to List<String> ---> Data Transformed
        //Mapping--> 1-to-1 as each Customer has only one email.
        //We can use map() here.
        List<String> emailList = customers.stream()
                .map(Customer::getEmail)
                .collect(Collectors.toList());

        System.out.println("Emails: "+emailList);

        //List<Customer> ---> each one has list of phone---> List<PhoneNumber> ----> Data Transformed
        //when we need to flatter the stream to single stream, we use flatMap().
        //Mapping --> 1-to-M as once customer has multiple phone numbers

        List<String> phoneNumbers = customers.stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .collect(Collectors.toList());

        System.out.println("Phones: "+phoneNumbers);


        //Demonstrate filter(), reduce() and collect() ops;
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        integers.stream()
                .filter(integer -> integer % 2 == 0)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

        List<Integer> oddNumbers = integers.stream()
                .filter(integer -> integer % 2 == 1)
                .collect(Collectors.toList());

        System.out.println(oddNumbers);




        //Demonstrate findFirst() & findAny();


        /// ===========SEQUENTIAL STREAM=================

        //Will return same customer each time, since it's deterministic.
        Optional<Customer> firstCustomer = customers.stream()
                .filter(customer -> customer.getName().length() <= 3)
                .findFirst();

        System.out.println(firstCustomer.get().getName());


        //Could return different values each time. Since it's non-deterministic
        Optional<Customer> anyCustomer = customers.stream()
                .filter(customer -> customer.getName().length() <= 3)
                .findAny();

        System.out.println(anyCustomer.get().getName());


        ///========PARALLEL STREAM============
        Optional<Customer> fCustomer = customers.parallelStream()
                .filter(customer -> customer.getName().length() <= 3)
                .findFirst();


        System.out.println(fCustomer.get().getName());


        Optional<Customer> anyCust = customers.parallelStream()
                .filter(customer -> customer.getName().length() <= 3)
                .findAny();

        System.out.println(anyCust.get().getName());


        ///  Sequential & Parallel Stream demonstration

        List<Integer> numbers = IntStream.range(1, 1000000)
                .boxed()
                .collect(Collectors.toList());

        // Sequential stream
        long start = System.currentTimeMillis();

        long sum1 = numbers.stream()
                .mapToLong(Integer::longValue)
                .sum();
        long sequential = System.currentTimeMillis() - start;

        // Parallel stream
        start = System.currentTimeMillis();

        long sum2 = numbers.parallelStream()
                .mapToLong(Integer::longValue)
                .sum();
        long parallel = System.currentTimeMillis() - start;

        System.out.println("Sequential: " + sequential + "ms"+ "Result: "+sum1);
        System.out.println("Parallel: " + parallel + "ms"+" Result: "+sum2);

    }
}
