package com.spike.spring.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author spike.lin
 */
@Service
public class AsyncTaskService {

    Random random = new Random();

    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务" + i);
    }

    @Async
    public Future<String> asyncInvokeReturnFuture(Integer i) throws InterruptedException {
        System.out.println("input is " + i);
        Thread.sleep(1000 * random.nextInt(i));

        Future<String> future = new AsyncResult<String>("success" + i);
        return future;
    }
}
