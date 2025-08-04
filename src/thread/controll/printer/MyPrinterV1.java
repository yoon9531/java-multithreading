package thread.controll.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static util.MyLogger.log;
import static util.ThreadUtils.sleep;

public class MyPrinterV1 {

    public static void main (String[] args) {
        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);
        while (true) {
            log("프린터할 문서를 입력하세요. 종료(q): ");
            String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.work = false;
                break;
            }

            printer.addJob(input); // 메인 스레드가 jobQueue에 작업을 넣어준다.
        }
    }

    static class Printer implements Runnable {
        volatile boolean work = true;
        // 여러 thread가 접근하는 queue이기 때문에 ConcurrentLinkedQueue 사용
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run () {
            while (work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                String job = jobQueue.poll();
                log("출력 시작: " + job + ", 대기 문서: " + jobQueue);
                sleep(3000);
                log("출력 완료");
            }

            log("출력 종료");
        }

        public void addJob (String input) {
            jobQueue.offer(input);
        }
    }
}
