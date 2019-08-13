package com.x.automower;

import com.google.inject.AbstractModule;
import com.x.automower.service.ConsoleMowerRenderer;
import com.x.automower.service.MowerRenderer;
import com.x.automower.service.SimulationInputReader;
import com.x.automower.service.SimulationInputReaderImpl;

public class AutoMowerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MowerRenderer.class).to(ConsoleMowerRenderer.class);
        bind(SimulationInputReader.class).to(SimulationInputReaderImpl.class);
    }
}
