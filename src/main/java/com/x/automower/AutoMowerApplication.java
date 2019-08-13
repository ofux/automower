package com.x.automower;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.x.automower.service.SimulationInitializer;

public class AutoMowerApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AutoMowerModule());
        var simulationInitializer = injector.getInstance(SimulationInitializer.class);
        var simulation = simulationInitializer.initialize(args);
        simulation.simulate();
    }

}
