package com.giorgosgaganis.fibers.scalability;

import com.giorgosgaganis.fibers.scalability.experiment.Experiment;
import com.giorgosgaganis.fibers.scalability.experiment.runners.FiberRunner;
import com.giorgosgaganis.fibers.scalability.experiment.runners.Runner;

public class FiberMain {


    public static void main(String[] args) {
        Runner runner = new FiberRunner();

        Experiment experiment = new Experiment(runner);

        experiment.start();
    }
}
