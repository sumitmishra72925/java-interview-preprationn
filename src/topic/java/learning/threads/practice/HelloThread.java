package topic.java.learning.threads.practice;

public class HelloThread extends Thread{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("Hello from thread: " +this.getName());
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        HelloThread t1 = new HelloThread();
        HelloThread t2 = new HelloThread();

        t1.setName("1st");
        t2.setName("2nd");

        t1.start();
        t2.start();
    }


}
