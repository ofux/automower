package com.x.automower.parsing;

import com.x.automower.simulation.navigation.MoveForwardInstruction;
import com.x.automower.simulation.navigation.NavigationInstruction;
import com.x.automower.simulation.navigation.TurnLeftInstruction;
import com.x.automower.simulation.navigation.TurnRightInstruction;

import java.util.Optional;

public class NavigationInstructionParser implements Parser<NavigationInstruction> {
    @Override
    public NavigationInstruction deserialize(String s) throws ParsingException {
        var instruction = fromCode(s);
        if (instruction.isEmpty()) {
            throw new ParsingException(s, NavigationInstruction.class);
        }

        return instruction.get();
    }

    private Optional<NavigationInstruction> fromCode(String code) {
        switch (code) {
            case "F":
                return Optional.of(new MoveForwardInstruction());
            case "L":
                return Optional.of(new TurnLeftInstruction());
            case "R":
                return Optional.of(new TurnRightInstruction());
            default:
                return Optional.empty();
        }
    }

    @Override
    public String serialize(NavigationInstruction instruction) {
        return instruction.getCode();
    }
}
