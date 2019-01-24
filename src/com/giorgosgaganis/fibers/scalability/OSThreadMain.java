package com.giorgosgaganis.fibers.scalability;

import com.giorgosgaganis.fibers.scalability.experiment.Experiment;
import com.giorgosgaganis.fibers.scalability.experiment.runners.OSThreadRunner;
import com.giorgosgaganis.fibers.scalability.experiment.runners.Runner;

public class OSThreadMain {


    public static void main(String[] args) {
        Runner runner = new OSThreadRunner();

        Experiment experiment = new Experiment(runner);

        experiment.start();
    }
}
