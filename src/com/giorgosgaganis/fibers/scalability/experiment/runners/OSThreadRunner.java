package com.giorgosgaganis.fibers.scalability.experiment.runners;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OSThreadRunner implements Runner {
    private static final int POOL_SIZE = 400;

    private final ExecutorService executorService =
            Executors.newFixedThreadPool(POOL_SIZE);

    @Override
    public void runRequest(Runnable runnable) {
        executorService.submit(runnable);
    }
}
