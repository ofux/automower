package com.x.automower;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.x.automower.parsing.*;
import com.x.automower.service.ConsoleMowerRenderer;
import com.x.automower.service.MowerRenderer;
import com.x.automower.service.SimulationInputReader;
import com.x.automower.service.SimulationInputReaderImpl;
import com.x.automower.simulation.math.Orientation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.NavigationInstruction;

public class AutoMowerModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MowerRenderer.class).to(ConsoleMowerRenderer.class);
        bind(SimulationInputReader.class).to(SimulationInputReaderImpl.class);
        bind(new TypeLiteral<Parser<Vector2D>>(){}).to(Vector2DParser.class);
        bind(new TypeLiteral<Parser<Orientation>>(){}).to(OrientationParser.class);
        bind(new TypeLiteral<Parser<Transform>>(){}).to(TransformParser.class);
        bind(new TypeLiteral<Parser<NavigationInstruction>>(){}).to(NavigationInstructionParser.class);
    }
}
