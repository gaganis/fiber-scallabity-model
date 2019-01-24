package com.giorgosgaganis.fibers.scalability.experiment.runners;

public interface Runner {
    void runRequest(Runnable runnable);
}
