package com.spike.spring.async;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestThreadPool {

    private static final Logger log = Logger.getLogger(TestThreadPool.class);

    @Test
    public void test() {
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200,
//                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5));
        ExecutorService fixedExecutorService = Executors.newFixedThreadPool(50);
        ExecutorService cachedThreadPoolService = Executors.newCachedThreadPool();
        ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
        List<Future> futures = new ArrayList<>();

        Long start = System.currentTimeMillis();

        for (int i = 0; i < 150; i++) {
            MyTask myTask = new MyTask(i);
            Future future = fixedExecutorService.submit(myTask);
            futures.add(future);
//            System.out.println("线程池中线程数目：" + executor.getPoolSize() + ",队列中等待执行的任务数目：" +
//            executor.getQueue().size() + ",已执行完别的任务数目：" + executor.getCompletedTaskCount());
        }

        for (int i = 0; i < futures.size(); i++) {
            while (true) {
                if (futures.get(i).isDone()) {
                    break;
                }
            }
        }

        Long end = System.currentTimeMillis();
        log.info("总耗时为：" + (end - start));

        fixedExecutorService.shutdown();
    }

    @Test
    public void test2() throws InterruptedException {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            MyTask myTask = new MyTask(i);
            Thread thread = new Thread(myTask);
            list.add(thread);
            thread.start();
        }

        for (Thread t : list) {
            t.join();
        }

        log.info("主线程完成");
    }

    class MyTask implements Runnable {

        private int taskNum;

        public MyTask(int taskNum) {
            this.taskNum = taskNum;
        }

        @Override
        public void run() {
            System.out.println("正在执行task " + taskNum);
            log.info("正在执行task " + taskNum);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File("D:\\y" + taskNum + ".txt"));
                int ch = 'a';
                while (true) {
                    fos.write(ch);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != fos) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.info("task " + taskNum + "执行完毕");
        }
    }
}
