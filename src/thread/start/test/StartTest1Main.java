package thread.start.test;

import static util.MyLogger.log;

public class StartTest1Main {
    public static void main (String[] args) {
        CounterThread counterThread = new CounterThread();
        counterThread.start();

        CounterRunnable runnable = new CounterRunnable();
        Thread thread = new Thread(runnable);
        thread.setName("counter");
        thread.start();
    }

    static class CounterThread extends Thread {
        @Override
        public void run () {
            for (int i = 1; i <= 5; i++) {
                log("value: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run () {
            for (int i = 1; i <= 5; i++) {
                log("value: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
