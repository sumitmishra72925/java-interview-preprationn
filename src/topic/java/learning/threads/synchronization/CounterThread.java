package topic.java.learning.threads.synchronization;

public class CounterThread implements Runnable{

    ValuePrinter valuePrinter;



    public CounterThread(ValuePrinter valuePrinter) {
        this.valuePrinter = valuePrinter;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10000; i++){
            valuePrinter.increment();
        }

    }
}
