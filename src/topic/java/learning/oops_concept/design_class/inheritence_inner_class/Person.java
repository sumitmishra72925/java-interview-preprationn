package topic.java.learning.oops_concept.design_class.inheritence_inner_class;

import java.util.Objects;

public class Person {

    private String name;
    private int age;
    private String occupation;

    public Person(String name) {
        this(name, 18);
    }

    public Person(String name, int age) {
       this(name,age, "Business");
    }

    public Person(String name, int age, String occupation) {
        this.name = name;
        this.age = age;
        this.occupation = occupation;
    }


    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof Person)) return false;
        if(obj == this) return true;
        Person person = (Person) obj;
        return person.name.equals(this.name) && person.age == this.age && person.occupation.equals(this.occupation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, occupation);
    }


    public static void main(String[] args) {
        Person sumit = new Person("Sumit", 31, "Software engineer");
        Person sumit2 = new Person("Sumit", 31, "Software engineer");
        System.out.println(sumit.equals(sumit2));
        System.out.println(sumit.hashCode());
        System.out.println(sumit2.hashCode());
    }
}
