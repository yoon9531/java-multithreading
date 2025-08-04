package thread.start;

import static util.MyLogger.log;

public class InnerRunnableMainV2 {

    public static void main (String[] args) {
        log("main() start");
        Runnable runnable = new Runnable() {
            @Override
            public void run () {
                log("run()");
            }
        }; // 위 기능을 이 메서드에서만 사용하고 싶을 경우

        Thread thread = new Thread(runnable);
        thread.start();

        log("main() end");
    }
}
