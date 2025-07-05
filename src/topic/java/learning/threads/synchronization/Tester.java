package topic.java.learning.threads.synchronization;

public class Tester {

    public static void main(String[] args) throws InterruptedException {
        ValuePrinter valuePrinter = new ValuePrinter();

        CounterThread t1Runnable = new CounterThread(valuePrinter);
        CounterThread t2Runnable = new CounterThread(valuePrinter);

        Thread t1 = new Thread(t1Runnable);
        Thread t2 = new Thread(t2Runnable);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final counter value: "+valuePrinter.getCounter());
    }
}
