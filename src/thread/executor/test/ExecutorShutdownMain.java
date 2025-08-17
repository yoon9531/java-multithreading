package thread.executor.test;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;

public class ExecutorShutdownMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);

        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("longTask", 100_00)); // 100초 대기
        printState(es);
        log("== shutdown 시작 ==");
        shutdownAndAwaitTermination(es);
        log("== shutdown 종료 ==");
        printState(es);
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {
        es.shutdown(); // non-blocking, 새로운 작업을 받지 않는다, 처리 중이거나 큐에 이미 대기 중인 작업은 처리 -> 이후에 풀의 스레드를 종료

        try {
            // 이미 대기 중인 작업들을 모두 오나료할 때까지 10초 대기
            if(!es.awaitTermination(10, TimeUnit.SECONDS)) {
                // 정상 종료가 너무 오래 걸릴 때
                log("서비스 정상 종료 실패 -> 강제 종료 시도");
                es.shutdownNow();
                // 작업이 취소될 때 까지 대기한다.
                // 인터럽트를 받을 수 없는 코드를 스레드가 수행 중일 때
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("서비스가 종료되지 않았습니다");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination()으로 대기 중인 스레드가 인터럽트 될 수 있다.
            es.shutdownNow();
        }
    }
}
