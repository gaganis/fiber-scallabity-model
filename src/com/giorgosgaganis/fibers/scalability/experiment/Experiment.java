package com.giorgosgaganis.fibers.scalability.experiment;

import com.giorgosgaganis.fibers.scalability.experiment.runners.Runner;
import com.giorgosgaganis.fibers.scalability.model.ExternalService;
import com.giorgosgaganis.fibers.scalability.model.ViewAccountPage;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Experiment {

    private static final int REQUESTS_PER_SECOND = 100;
    private AtomicLong replyCounter = new AtomicLong(0);

    private final Runner runner;

    public Experiment(Runner runner) {
        this.runner = runner;
    }

    private void runExperimentWithRunner(Runner runner,
                                         ViewAccountPage viewAccountPage) {

        while (!Thread.interrupted()) {

            Runnable runnable = () -> {

                long randomLong = new Random().nextLong();
                String accountNumber = Long.toString(randomLong);

                viewAccountPage.render(accountNumber);
                replyCounter.incrementAndGet();

            };

            runner.runRequest(runnable);

            try {
                Thread.sleep(1000 / REQUESTS_PER_SECOND);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void startMonitoringThread() {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                long startCount = replyCounter.get();
                long startTime = System.currentTimeMillis();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }

                long deltaTime = System.currentTimeMillis() - startTime;
                long replyDelta = replyCounter.get() - startCount;
                long responsesPerSecond = (replyDelta * 1000) / deltaTime;

                System.out.println("responsesPerSecond = "
                        + responsesPerSecond);

            }
        }).start();
    }


    public void start() {
        ViewAccountPage viewAccountPage = setup();

        startMonitoringThread();
        runExperimentWithRunner(runner, viewAccountPage);
    }

    private ViewAccountPage setup() {
        return new ViewAccountPage(new ExternalService());
    }
}
