package topic.java.learning.collections_generics;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionTest {

    public static void main(String[] args) {
        System.out.println(concatinateString("Hari"," Om", " Namo", " Bhagwate", " Vasudevaye"));
    }


    /**
     * Write a method concatinateString which takes varibale argument as an input and returns a single string
     * @param var: A variable length input
     * @return String: The final single string
     *
     *
     * */
    public static String concatinateString(String ...var){
        StringBuilder builder = new StringBuilder();
        for(String s : var){
            builder.append(s);
        }
        return builder.toString();
    }

    public static void sortString(List<String> stringsList){
        stringsList.sort((o1, o2) -> 0);

        System.out.println(stringsList);


    }

    public static int frequency(List<Integer> list, int target){
        return Collections.frequency(list, target);
    }






}
