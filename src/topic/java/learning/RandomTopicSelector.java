package topic.java.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomTopicSelector {

    public static void main(String[] args) {

        List<String> topics = new ArrayList<>();

        topics.add("OOP: 1");
        topics.add("Data Types and Variables");
        topics.add("Control Structures and Exception Handling");
        topics.add("Core Collections");
        topics.add("Advanced Collections Topics");
        topics.add("Thread Fundamentals");
        topics.add("Executor Framework");
        topics.add("Concurrent Collections and Synchronization");
        topics.add("Garbage Collection");
        topics.add("Advanced JVM Questions");
        topics.add("Lambda Expressions and Functional Programming");
        topics.add("Streams API");
        topics.add("Optional and Other Java 8+ Features");
        topics.add("Reflection and Annotations");
        topics.add("Basic Generics Questions:");
        topics.add("Creational Patterns:");
        topics.add("Structural Patterns");
        topics.add("Behavioral Patterns");
        topics.add("Coding Standards:");
        topics.add("Application Design:");
        topics.add("Performance Problems:");
        topics.add("Data Structure Implementation:");


        Random random = new Random();
        int randomNum = random.nextInt(topics.size());

        System.out.println("Today's topic is: "+topics.get(randomNum));
    }
}
