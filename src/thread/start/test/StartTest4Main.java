package thread.start.test;

public class StartTest4Main {
    public static void main (String[] args) {
        Thread threadA = new Thread(new PrintThread("A", 1000), "Thread-A");
        Thread threadB = new Thread(new PrintThread("B", 500), "Thread-B");

        threadA.start();
        threadB.start();
    }

    static class PrintThread implements Runnable {

        private String content;
        private int sleepMs;

        public PrintThread (String content, int sleepMs) {
            this.content = content;
            this.sleepMs = sleepMs;
        }

        @Override
        public void run () {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(content);
                try {
                    Thread.sleep(sleepMs);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
