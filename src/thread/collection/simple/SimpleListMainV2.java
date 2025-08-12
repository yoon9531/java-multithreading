package thread.collection.simple;

import thread.collection.simple.list.BasicList;
import thread.collection.simple.list.SimpleList;
import thread.collection.simple.list.SyncList;
import thread.collection.simple.list.SyncProxyList;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicList());
//        test(new SyncList());
        BasicList basicList = new BasicList();
        SyncProxyList syncProxyList = new SyncProxyList(basicList);
        test(syncProxyList);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSigners());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread t1 = new Thread(addA, "Thread-1");
        Thread t2 = new Thread(addB, "Thread-2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log(list);
    }
}
