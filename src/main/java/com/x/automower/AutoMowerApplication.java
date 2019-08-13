package com.x.automower;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.x.automower.service.SimulationInputReader;

import java.io.FileInputStream;
import java.io.IOException;

public class AutoMowerApplication {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Expecting valid file name as first argument");
        }

        Injector injector = Guice.createInjector(new AutoMowerModule());

        try (var fileInputStream = new FileInputStream(args[0])) {
            var simulationInitializer = injector.getInstance(SimulationInputReader.class);
            var simulation = simulationInitializer.read(fileInputStream);
            simulation.simulate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
