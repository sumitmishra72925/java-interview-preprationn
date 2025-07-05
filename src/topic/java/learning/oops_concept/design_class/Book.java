package topic.java.learning.oops_concept.design_class;

import java.util.List;
import java.util.Objects;

public class Book {

    private static int totalBooks;

    static {
        System.out.println("Static block executed!!");
        totalBooks = 0;
    }

    //Whenever any object of this class will be created, We will increment the totalBook by 1.
    {
        System.out.println("Init block executed!!");
        totalBooks++;
    }


    private String title;
    private String author;
    private String isbn;
    boolean isBorrowed;



    /* Example of constructor chaining.*/
    public Book(){
        this("Default Book");
    }

    public Book(String title){
        this(title, "Default author");
    }

    public Book(String title, String author){
        this(title, author, "Default ISBN");
    }

    public Book(String title, String author, String isbn){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        System.out.println("Book successfully created with name: "+title+" by author Shri: "+author);
    }

    public void borrowBook(){
        if(isBorrowed){
            System.out.println(this.title.toUpperCase()+" is already borrowed!!");
        }else {
            this.isBorrowed = true;
            System.out.println("Enjoy the epic "+this.title.toUpperCase());
        }

    }

    public void returnBook(){
        if(isBorrowed){
            System.out.println("Hope you enjoyed the epic: "+this.title.toUpperCase());
        }else {
            System.out.println(this.title.toUpperCase()+" is already available in Library!!");
        }
    }

    static int getTotalBook(){
        return totalBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, isbn);
    }

    public static void main(String[] args) {
        Book ramayana = new Book("Ramayana", "Maharishi Valmiki", "NOT_KNOWN");
        ramayana.borrowBook();
        ramayana.returnBook();
        System.out.println(Book.getTotalBook());
    }




}

class Course{


    private static int maxCapacity;

    static {
        maxCapacity = 100;
    }

    {
        this.teacher = "Teacher X";
    }

    Course(){
        throw new RuntimeException("Course name is mandatory");
    }
    Course(String courseName){
        this(courseName, 1);
    }

    Course(String courseName, int duration){
        this(courseName, duration, 10);
    }

    Course(String courseName, int duration, int capacity){
        this.courseName = courseName;
        this.duration = duration;
        this.capacity = capacity;
    }


    String courseName;
    List<String> studentsEnrolled; //list of student
    int capacity;
    int duration;
    String teacher;


    String getCourseName(){
        return this.courseName;
    }

    String getTeacherName(){
        return "The teacher for course: "+this.courseName+" is: "+this.teacher;
    }

    String enrollIntoCourse(){
        System.out.println("Max capacity: "+maxCapacity+" Current capacity: "+capacity);
        if(capacity < maxCapacity){
            capacity++;
            return "You have successfully enrolled into course: "+this.courseName;
        }else {
            return "Sorry!! no more slots are available";
        }
    }


    public static void main(String[] args) {
        Course codingCourse = new Course("Coding Course");
        System.out.println(codingCourse.getCourseName());
        System.out.println(codingCourse.teacher);
        System.out.println(codingCourse.getTeacherName());
        System.out.println(codingCourse.enrollIntoCourse());

    }


}
