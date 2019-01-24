package com.giorgosgaganis.fibers.scalability.experiment.runners;

public class FiberRunner implements Runner {

    @Override
    public void runRequest(Runnable runnable) {
        Fiber.schedule(runnable);
    }
}
