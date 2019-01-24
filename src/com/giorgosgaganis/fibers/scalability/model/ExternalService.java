package com.giorgosgaganis.fibers.scalability.model;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class ExternalService {
    private static final int FAILURE_RATE = 10;
    private static final int TIMEOUT_MINUTES = 4;
    private static final long RESPONSE_MILLIS = 100L;

    AtomicInteger invocationCounter = new AtomicInteger(0);

    public String getAccountInfo(String accountNumber) {
        try {
            if (invocationCounter.incrementAndGet() % FAILURE_RATE == 0) {

                long timeOutMillis = Duration.ofMinutes(TIMEOUT_MINUTES)
                        .toMillis();
                Thread.sleep(timeOutMillis);
            } else {
                Thread.sleep(RESPONSE_MILLIS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
            return null;
        }
        return "This is the information for the account: " + accountNumber;
    }
}
