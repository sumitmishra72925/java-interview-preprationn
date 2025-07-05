package topic.java.learning.threads.lifecycle;

import java.util.concurrent.*;

public class ThreadLifecycleDemo {
    
    // Shared resources for demonstration
    private static final Object sharedLock = new Object();
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static volatile boolean shouldContinue = true;
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== COMPLETE THREAD LIFECYCLE DEMONSTRATION ===\n");
        
        demonstrateNewState();
        Thread.sleep(1000);
        
        demonstrateRunnableState();
        Thread.sleep(1000);
        
        demonstrateBlockedState();
        Thread.sleep(1000);
        
        demonstrateWaitingState();
        Thread.sleep(1000);
        
        demonstrateTimedWaitingState();
        Thread.sleep(1000);
        
        demonstrateTerminatedState();
    }
    
    // 1. NEW STATE - Thread created but not started
    private static void demonstrateNewState() {
        System.out.println("1. === NEW STATE DEMONSTRATION ===");
        
        Thread newThread = new Thread(() -> {
            System.out.println("This will execute when thread starts");
        });
        
        System.out.println("Thread state after creation: " + newThread.getState());
        System.out.println("Thread ID: " + newThread.getId());
        System.out.println("Thread name: " + newThread.getName());
        System.out.println("Is alive: " + newThread.isAlive());
        
        // Methods available in NEW state:
        System.out.println("\nMethods available in NEW state:");
        System.out.println("- setName(), getName()");
        System.out.println("- setPriority(), getPriority()");
        System.out.println("- setDaemon(), isDaemon()");
        System.out.println("- start() - transitions to RUNNABLE");
        
        // Transition to RUNNABLE state
        newThread.start();
        System.out.println("After start(): " + newThread.getState());
        
        try {
            newThread.join(); // Wait for completion
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("NEW → RUNNABLE → TERMINATED\n");
    }
    
    // 2. RUNNABLE STATE - Thread is executing or ready to execute
    private static void demonstrateRunnableState() throws InterruptedException {
        System.out.println("2. === RUNNABLE STATE DEMONSTRATION ===");
        
        Thread runnableThread = new Thread(() -> {
            System.out.println("Thread entered RUNNABLE state");
            System.out.println("Current state: " + Thread.currentThread().getState());
            
            // Simulate CPU-intensive work
            for (int i = 0; i < 5; i++) {
                System.out.println("Working... " + i);
                // Busy work to stay in RUNNABLE state
                for (int j = 0; j < 1000000; j++) {
                    Math.sqrt(j); // CPU work
                }
            }
            
            System.out.println("Thread finishing RUNNABLE work");
        });
        
        runnableThread.setName("RunnableDemo");
        System.out.println("Before start: " + runnableThread.getState());
        
        runnableThread.start();
        
        // Check state while running
        Thread.sleep(100);
        System.out.println("While executing: " + runnableThread.getState());
        
        System.out.println("\nMethods that keep thread in RUNNABLE:");
        System.out.println("- Normal code execution");
        System.out.println("- CPU-intensive operations");
        System.out.println("- Non-blocking I/O operations");
        
        runnableThread.join();
        System.out.println("RUNNABLE → TERMINATED\n");
    }
    
    // 3. BLOCKED STATE - Thread waiting for monitor lock
    private static void demonstrateBlockedState() throws InterruptedException {
        System.out.println("3. === BLOCKED STATE DEMONSTRATION ===");
        
        Thread lockHolder = new Thread(() -> {
            synchronized (sharedLock) {
                System.out.println("Thread 1 acquired lock, holding for 3 seconds...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Thread 1 releasing lock");
            }
        });
        
        Thread blockedThread = new Thread(() -> {
            System.out.println("Thread 2 attempting to acquire lock...");
            synchronized (sharedLock) {
                System.out.println("Thread 2 finally acquired lock!");
            }
        });
        
        lockHolder.setName("LockHolder");
        blockedThread.setName("BlockedThread");
        
        // Start lock holder first
        lockHolder.start();
        Thread.sleep(500); // Let it acquire the lock
        
        // Start blocked thread
        blockedThread.start();
        Thread.sleep(500); // Let it try to acquire lock
        
        System.out.println("BlockedThread state: " + blockedThread.getState());
        
        System.out.println("\nMethods that cause BLOCKED state:");
        System.out.println("- synchronized method/block when lock is held by another thread");
        System.out.println("- Implicit monitor entry");
        
        System.out.println("\nTransition out of BLOCKED:");
        System.out.println("- When the lock becomes available");
        
        lockHolder.join();
        blockedThread.join();
        System.out.println("RUNNABLE → BLOCKED → RUNNABLE → TERMINATED\n");
    }
    
    // 4. WAITING STATE - Thread waiting indefinitely
    private static void demonstrateWaitingState() throws InterruptedException {
        System.out.println("4. === WAITING STATE DEMONSTRATION ===");
        
        Object waitObject = new Object();
        
        Thread waitingThread = new Thread(() -> {
            synchronized (waitObject) {
                try {
                    System.out.println("Thread entering WAITING state...");
                    waitObject.wait(); // Indefinite wait
                    System.out.println("Thread woke up from WAITING state");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Thread notifyingThread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                synchronized (waitObject) {
                    System.out.println("Notifying waiting thread...");
                    waitObject.notify();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        waitingThread.setName("WaitingThread");
        notifyingThread.setName("NotifyingThread");
        
        waitingThread.start();
        Thread.sleep(500);
        
        System.out.println("WaitingThread state: " + waitingThread.getState());
        
        System.out.println("\nMethods that cause WAITING state:");
        System.out.println("- Object.wait() without timeout");
        System.out.println("- Thread.join() without timeout");
        System.out.println("- LockSupport.park()");
        System.out.println("- CountDownLatch.await()");
        System.out.println("- CyclicBarrier.await()");
        
        notifyingThread.start();
        
        waitingThread.join();
        notifyingThread.join();
        System.out.println("RUNNABLE → WAITING → RUNNABLE → TERMINATED\n");
    }
    
    // 5. TIMED_WAITING STATE - Thread waiting for specific time
    private static void demonstrateTimedWaitingState() throws InterruptedException {
        System.out.println("5. === TIMED_WAITING STATE DEMONSTRATION ===");
        
        Thread timedWaitingThread = new Thread(() -> {
            try {
                System.out.println("Thread entering TIMED_WAITING state for 3 seconds...");
                Thread.sleep(3000);
                System.out.println("Thread woke up from TIMED_WAITING state");
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted during TIMED_WAITING");
                Thread.currentThread().interrupt();
            }
        });
        
        timedWaitingThread.setName("TimedWaitingThread");
        timedWaitingThread.start();
        
        Thread.sleep(500);
        System.out.println("TimedWaitingThread state: " + timedWaitingThread.getState());
        
        System.out.println("\nMethods that cause TIMED_WAITING state:");
        System.out.println("- Thread.sleep(milliseconds)");
        System.out.println("- Object.wait(timeout)");
        System.out.println("- Thread.join(timeout)");
        System.out.println("- LockSupport.parkNanos()");
        System.out.println("- LockSupport.parkUntil()");
        System.out.println("- CountDownLatch.await(timeout, unit)");
        
        // Demonstrate different timed waiting methods
        demonstrateJoinWithTimeout();
        demonstrateWaitWithTimeout();
        
        timedWaitingThread.join();
        System.out.println("RUNNABLE → TIMED_WAITING → RUNNABLE → TERMINATED\n");
    }
    
    private static void demonstrateJoinWithTimeout() throws InterruptedException {
        System.out.println("\n--- Join with timeout example ---");
        
        Thread longRunningThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        Thread joiningThread = new Thread(() -> {
            try {
                System.out.println("Joining with 2-second timeout...");
                longRunningThread.join(2000); // TIMED_WAITING
                System.out.println("Join timeout completed");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        longRunningThread.start();
        joiningThread.start();
        
        Thread.sleep(500);
        System.out.println("JoiningThread state: " + joiningThread.getState());
        
        joiningThread.join();
        longRunningThread.interrupt(); // Clean up
        longRunningThread.join();
    }
    
    private static void demonstrateWaitWithTimeout() throws InterruptedException {
        System.out.println("\n--- Wait with timeout example ---");
        
        Object timeoutObject = new Object();
        
        Thread timeoutWaitingThread = new Thread(() -> {
            synchronized (timeoutObject) {
                try {
                    System.out.println("Waiting with 2-second timeout...");
                    timeoutObject.wait(2000); // TIMED_WAITING
                    System.out.println("Wait timeout completed");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        timeoutWaitingThread.start();
        Thread.sleep(500);
        System.out.println("TimeoutWaitingThread state: " + timeoutWaitingThread.getState());
        
        timeoutWaitingThread.join();
    }
    
    // 6. TERMINATED STATE - Thread completed execution
    private static void demonstrateTerminatedState() throws InterruptedException {
        System.out.println("6. === TERMINATED STATE DEMONSTRATION ===");
        
        Thread terminatedThread = new Thread(() -> {
            System.out.println("Thread is about to terminate...");
            System.out.println("Current state before return: " + Thread.currentThread().getState());
        });
        
        terminatedThread.setName("TerminatedThread");
        
        System.out.println("Before start: " + terminatedThread.getState());
        terminatedThread.start();
        
        terminatedThread.join(); // Wait for completion
        
        System.out.println("After completion: " + terminatedThread.getState());
        System.out.println("Is alive: " + terminatedThread.isAlive());
        
        System.out.println("\nWays thread reaches TERMINATED state:");
        System.out.println("- Normal completion of run() method");
        System.out.println("- Uncaught exception in run() method");
        System.out.println("- Thread.stop() (deprecated - don't use!)");
        
        // Demonstrate exception causing termination
        Thread exceptionThread = new Thread(() -> {
            System.out.println("Thread about to throw exception...");
            throw new RuntimeException("Intentional exception for demo");
        });
        
        exceptionThread.setName("ExceptionThread");
        exceptionThread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Exception caught: " + e.getMessage());
            System.out.println("Thread state after exception: " + t.getState());
        });
        
        exceptionThread.start();
        exceptionThread.join();
        
        System.out.println("ExceptionThread final state: " + exceptionThread.getState());
        System.out.println("\nRUNNABLE → TERMINATED (via exception)");
        
        System.out.println("\n=== THREAD LIFECYCLE SUMMARY ===");
        printLifecycleSummary();
    }
    
    private static void printLifecycleSummary() {
        System.out.println("Thread States and Transitions:");
        System.out.println("NEW → RUNNABLE (start())");
        System.out.println("RUNNABLE → BLOCKED (synchronized lock contention)");
        System.out.println("BLOCKED → RUNNABLE (lock acquired)");
        System.out.println("RUNNABLE → WAITING (wait(), join(), park())");
        System.out.println("WAITING → RUNNABLE (notify(), interrupt())");
        System.out.println("RUNNABLE → TIMED_WAITING (sleep(), wait(timeout), join(timeout))");
        System.out.println("TIMED_WAITING → RUNNABLE (timeout expires, interrupt())");
        System.out.println("Any state → TERMINATED (run() completes, exception, stop())");
        
        System.out.println("\nKey Methods by State:");
        System.out.println("NEW: setName(), setPriority(), setDaemon(), start()");
        System.out.println("RUNNABLE: normal execution, yield()");
        System.out.println("BLOCKED: waiting for synchronized lock");
        System.out.println("WAITING: wait(), join(), await(), park()");
        System.out.println("TIMED_WAITING: sleep(), wait(timeout), join(timeout)");
        System.out.println("TERMINATED: thread finished, isAlive() returns false");
    }

    public static boolean isShouldContinue() {
        return shouldContinue;
    }

    public static void setShouldContinue(boolean shouldContinue) {
        ThreadLifecycleDemo.shouldContinue = shouldContinue;
    }
}

// Additional class to demonstrate advanced lifecycle scenarios
class AdvancedLifecycleScenarios {
    
    public static void demonstrateInterruptionDuringStates() throws InterruptedException {
        System.out.println("\n=== INTERRUPTION DURING DIFFERENT STATES ===");
        
        // Interrupting TIMED_WAITING thread
        Thread sleepingThread = new Thread(() -> {
            try {
                System.out.println("Thread sleeping (TIMED_WAITING)...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted!");
                System.out.println("State after interrupt: " + Thread.currentThread().getState());
            }
        });
        
        sleepingThread.start();
        Thread.sleep(1000);
        sleepingThread.interrupt(); // Interrupt while in TIMED_WAITING
        sleepingThread.join();
        
        // Interrupting WAITING thread
        Object waitObj = new Object();
        Thread waitingThread = new Thread(() -> {
            synchronized (waitObj) {
                try {
                    System.out.println("Thread waiting (WAITING)...");
                    waitObj.wait();
                } catch (InterruptedException e) {
                    System.out.println("Wait interrupted!");
                    System.out.println("State after interrupt: " + Thread.currentThread().getState());
                }
            }
        });
        
        waitingThread.start();
        Thread.sleep(1000);
        waitingThread.interrupt(); // Interrupt while in WAITING
        waitingThread.join();
    }
    
    public static void demonstrateThreadTransitionMonitoring() throws InterruptedException {
        System.out.println("\n=== REAL-TIME STATE MONITORING ===");
        
        Thread monitoredThread = new Thread(() -> {
            try {
                // Simulate various state transitions
                System.out.println("1. Starting work (RUNNABLE)");
                Thread.sleep(1000); // TIMED_WAITING
                
                System.out.println("2. Doing CPU work (RUNNABLE)");
                for (int i = 0; i < 100000000; i++) {
                    Math.sqrt(i); // CPU intensive work
                }
                
                System.out.println("3. Sleeping again (TIMED_WAITING)");
                Thread.sleep(1000);
                
                System.out.println("4. Finishing (RUNNABLE → TERMINATED)");
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        // Monitor thread in separate thread
        Thread monitor = new Thread(() -> {
            Thread.State lastState = null;
            while (monitoredThread.isAlive() || monitoredThread.getState() != Thread.State.TERMINATED) {
                Thread.State currentState = monitoredThread.getState();
                if (currentState != lastState) {
                    System.out.println("State changed to: " + currentState + 
                                     " at " + System.currentTimeMillis());
                    lastState = currentState;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        
        monitor.start();
        monitoredThread.start();
        
        monitoredThread.join();
        monitor.interrupt();
        monitor.join();
    }
}