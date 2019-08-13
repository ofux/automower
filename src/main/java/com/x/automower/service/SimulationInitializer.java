package com.x.automower.service;

import com.x.automower.simulation.Simulation;

public interface SimulationInitializer {
    Simulation initialize(String[] args);
}
