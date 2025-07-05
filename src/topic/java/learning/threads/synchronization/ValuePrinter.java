package topic.java.learning.threads.synchronization;

public class ValuePrinter {

    private int counter = 0;

    //Provides more encapsulation and prevents any unusual access
    private final Object lock = new Object();

    /**
     * <b>Best Practice:</b> </br> </br> Always Use private lock objects for better encapsulation and to prevent external interference with your synchronization logic.
     * */
    public synchronized void increment(){
        synchronized (lock){
            counter++;
        }
    }

    public int getCounter(){
        return counter;
    }
}
