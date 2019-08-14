package com.x.automower.service;

import com.x.automower.parsing.Parser;
import com.x.automower.simulation.Mower;
import com.x.automower.simulation.math.Transform;

import javax.inject.Inject;

public class ConsoleMowerRenderer implements MowerRenderer {

    private final Parser<Transform> transformParser;

    @Inject
    public ConsoleMowerRenderer(Parser<Transform> transformParser) {
        this.transformParser = transformParser;
    }

    @Override
    public void render(Mower mower) {
        System.out.println(transformParser.serialize(mower.getTransform()));
    }
}
