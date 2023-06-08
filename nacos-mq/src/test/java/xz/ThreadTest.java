package xz;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.C;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collectors;

public class ThreadTest {
    final static int threshold = 75;
    volatile static int cur = 0;
    volatile static int state = 0;

    public static void main(String[] args) throws Exception {
        // testThread();
        testQueue();
//        testArrayList();
    }

    public static void testArrayList() {
        int[] test = new int[]{1, 10, 30, 34, 5, 9};

        System.out.println(Arrays.asList(test)
                .stream()
                .sorted((n, n1) -> (n + "" + n1).compareTo((n1 + "" + n)))
                .map(n -> String.valueOf(n))
                .collect(Collectors.joining()));
    }

    public static void testQueue() throws InterruptedException {
        SynchronousQueue que = new SynchronousQueue();
        SynchronousQueue que1 = new SynchronousQueue();
        SynchronousQueue que2 = new SynchronousQueue();
        Object val = new Object();
        new Thread(() -> {
            try {
                que.take();
                while(cur < threshold) {
                        System.out.println(Thread.currentThread().getName() + " " + ++cur );
                        if (cur % 5 == 0) {
                            que1.put(val);
                            que.take();
                        }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                que1.take();
                while(cur < threshold) {
                    System.out.println(Thread.currentThread().getName() + " " + ++cur );
                    if (cur % 5 == 0) {
                        que2.put(val);
                        que1.take();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                que2.take();
                while(cur < threshold) {
                    System.out.println(Thread.currentThread().getName() + " " + ++cur );
                    if (cur % 5 == 0) {
                        que.put(val);
                        que2.take();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        que.put(val);
    }

    public static void testThread() {
        CountDownLatch count = new CountDownLatch(1);
        count.countDown();

        new Thread(() -> {
            while(cur < threshold) {
                while(state == 0 && cur < threshold) {
                    System.out.println(Thread.currentThread().getName() + " " + ++cur );
                    if (cur % 5 == 0) {
                        state = 1;
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while(cur < threshold) {
                while(state == 1) {
                    System.out.println(Thread.currentThread().getName() + " " + ++cur );
                    if (cur % 5 == 0) {
                        state = 2;
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while(cur < threshold) {
                while(state == 2) {
                    System.out.println(Thread.currentThread().getName() + " " + ++cur );
                    if (cur % 5 == 0) {
                        state = 0;
                    }
                }
            }
        }).start();
    }
}
