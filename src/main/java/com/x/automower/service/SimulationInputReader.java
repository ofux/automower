package com.x.automower.service;

import com.x.automower.simulation.Simulation;

import java.io.InputStream;

public interface SimulationInputReader {
    Simulation read(InputStream inputStream);
}
