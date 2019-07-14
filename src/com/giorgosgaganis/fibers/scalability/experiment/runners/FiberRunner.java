package com.giorgosgaganis.fibers.scalability.experiment.runners;

public class FiberRunner implements Runner {
    FiberScope scope = FiberScope.open();

    @Override
    public void runRequest(Runnable runnable) {
        scope.schedule(runnable);
    }
}
