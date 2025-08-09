package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String>  queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) { // producer2 스레드는 consumer가 데이터를 소비할 때까지 무한 대기(TIMED_WAITING)
            log("[put] 큐가 가득 참, 생산자 대기 ");
            sleep(1000);
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {  // 이전에 producer 스레드가 TIMED_WAITING 상태이고, 대기하고 있으므로 consumer 스레드는 모두 producer 스레드가 synchronized 메서드 블록에서 락을 반환할 때까지 대기해야 함
            log("[take] 큐에 데이터가 없음, 소비자 대기");
            sleep(1000);
        }

        return queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
