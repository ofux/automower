package com.x.automower.service;

import com.x.automower.parsing.TransformParser;
import com.x.automower.simulation.Mower;

import javax.inject.Inject;

public class ConsoleMowerRenderer implements MowerRenderer {

    private final TransformParser transformParser;

    @Inject
    public ConsoleMowerRenderer(TransformParser transformParser) {
        this.transformParser = transformParser;
    }

    @Override
    public void render(Mower mower) {
        System.out.println(transformParser.serialize(mower.getTransform()));
    }
}
