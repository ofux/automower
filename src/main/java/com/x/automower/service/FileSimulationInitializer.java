package com.x.automower.service;

import com.x.automower.parsing.NavigationInstructionParser;
import com.x.automower.parsing.ParsingException;
import com.x.automower.parsing.TransformParser;
import com.x.automower.parsing.Vector2DParser;
import com.x.automower.simulation.Mower;
import com.x.automower.simulation.Simulation;
import com.x.automower.simulation.navigation.NavigationInstruction;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileSimulationInitializer implements SimulationInitializer {

    private final Vector2DParser vector2DParser;
    private final TransformParser transformParser;
    private final NavigationInstructionParser navigationInstructionParser;
    private final MowerRenderer mowerRenderer;

    @Inject
    public FileSimulationInitializer(Vector2DParser vector2DParser, TransformParser transformParser, NavigationInstructionParser navigationInstructionParser, MowerRenderer mowerRenderer) {
        this.vector2DParser = vector2DParser;
        this.transformParser = transformParser;
        this.navigationInstructionParser = navigationInstructionParser;
        this.mowerRenderer = mowerRenderer;
    }

    @Override
    public Simulation initialize(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Expecting valid file name as first argument");
        }

        try (var fileInputStream = new FileInputStream(args[0])) {
            try (var br = new BufferedReader(new InputStreamReader(fileInputStream))) {
                return readFile(br);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Simulation readFile(BufferedReader br) throws IOException, ParsingException {
        String line = br.readLine();
        var lawnSize = vector2DParser.deserialize(line);

        var mowers = new ArrayList<Mower>();
        while ((line = br.readLine()) != null) {
            var mowerTransform = transformParser.deserialize(line);
            var navigationInstructionsLine = br.readLine();
            if (navigationInstructionsLine != null) {
                var navigationInstructions = readNavigationInstructions(navigationInstructionsLine);
                mowers.add(new Mower(mowerTransform, navigationInstructions));
            }
        }

        return new Simulation(lawnSize, mowers, mowerRenderer);
    }

    private List<NavigationInstruction> readNavigationInstructions(String line) {
        return line.codePoints()
                .mapToObj(cp -> navigationInstructionParser.deserialize(new String(Character.toChars(cp))))
                .collect(Collectors.toList());
    }
}
