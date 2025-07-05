package topic.java.learning.oops_concept.design_class.inheritence_inner_class;

import java.util.Arrays;

public class ArrayOperations {

    private int []  sample = {1, 4, 2, 6, 3};

    private static class Statistics{

          double getMean(int [] input){
            return Arrays.stream(input).average().getAsDouble();
        }

         double getMedian(int [] input, int average){
            Arrays.sort(input);
            return input[input.length / 2];
        }

    }

    public static void main(String[] args) {
//        ArrayOperations arrayOperations = new ArrayOperations();
//
//        ArrayOperations.Statistics
    }




}
