package thread.executor;

import java.util.concurrent.*;

import static thread.executor.ExecutorUtils.printState;
import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) {
        // 스레드를 미리 만들어 두지는 않고, 작업이 들어오면 스레드를 생성하여 작업을 수행하는 ExecutorService를 생성.
        ExecutorService es = new ThreadPoolExecutor(2, 2, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        log("===== 초기 상태 =====");
        printState(es);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        log("===== 작업 수행 중 ======");
        printState(es);

        sleep(3000);
        log("===== 작업 수행 완료 =====");
        printState(es);

        es.close();
        log("===== shutdown 완료 =====");
        printState(es);
    }
}
