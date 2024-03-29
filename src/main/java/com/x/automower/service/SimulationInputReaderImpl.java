package com.x.automower.service;

import com.x.automower.parsing.Parser;
import com.x.automower.parsing.ParsingException;
import com.x.automower.simulation.Mower;
import com.x.automower.simulation.Simulation;
import com.x.automower.simulation.math.Transform;
import com.x.automower.simulation.math.Vector2D;
import com.x.automower.simulation.navigation.NavigationInstruction;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimulationInputReaderImpl implements SimulationInputReader {

    private final Parser<Vector2D> vector2DParser;
    private final Parser<Transform> transformParser;
    private final Parser<NavigationInstruction> navigationInstructionParser;
    private final MowerRenderer mowerRenderer;

    @Inject
    public SimulationInputReaderImpl(Parser<Vector2D> vector2DParser, Parser<Transform> transformParser, Parser<NavigationInstruction> navigationInstructionParser, MowerRenderer mowerRenderer) {
        this.vector2DParser = vector2DParser;
        this.transformParser = transformParser;
        this.navigationInstructionParser = navigationInstructionParser;
        this.mowerRenderer = mowerRenderer;
    }

    @Override
    public Simulation read(InputStream inputStream) {
        try (var br = new BufferedReader(new InputStreamReader(inputStream))) {
            return readBuffered(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Simulation readBuffered(BufferedReader br) throws IOException, ParsingException {
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
