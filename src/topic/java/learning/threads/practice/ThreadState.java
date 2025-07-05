package topic.java.learning.threads.practice;

public class ThreadState extends Thread {

    private final  Object lock = new Object();

    @Override
    public void run() {
        try {
           synchronized (lock){
               if(this.getName().equals("red")){
                   System.out.println("Signal is currently RED, Pull your Hand break!! & wait...");
               }else if(this.getName().equals("yellow")){
                   System.out.println("Signal is YELLOW now, start the car, get ready & wait for it to become green.." );
               }else {
                   System.out.println("Signal is GREEN now, You can go. Bye! Bye!");
               }
               Thread.sleep(5000);
           }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        char c = ' ';

    }

    public static void main(String[] args) throws InterruptedException {

        ThreadState redThread = new ThreadState();
        redThread.setName("red");

        ThreadState yellowThread = new ThreadState();
        yellowThread.setName("yellow");

        ThreadState greenThread = new ThreadState();
        greenThread.setName("green");

        redThread.start();
        redThread.join();
        yellowThread.start();
        yellowThread.join();
        greenThread.start();
        greenThread.join();






    }
}
