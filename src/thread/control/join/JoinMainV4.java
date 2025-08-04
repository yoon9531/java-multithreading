package thread.control.join;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class JoinMainV4 {
    public static void main (String[] args) throws InterruptedException {
        log("Start");

        SumTask sumTask1 = new SumTask(1, 50);
        Thread thread1 = new Thread(sumTask1, "thread-1");

        thread1.start();

        log("join() - main 스레드가 thread1 종료까지 1초 대기");
        thread1.join(1000);
        log("main 스레드 대기 완료");

        log("task1.result = " + sumTask1.result);
        log("End");
    }

    static class SumTask implements Runnable {
        int startVal;
        int endVal;
        int result = 0;

        public SumTask (int startVal, int endVal) {
            this.startVal = startVal;
            this.endVal = endVal;
        }

        @Override
        public void run () {
            log("Task Start");
            sleep(2000);
            int sum = 0;
            for (int i = startVal; i <= endVal; i++) {
                sum += i;
            }
            result = sum;
            log("Task End result = " + result);
        }
    }
}
