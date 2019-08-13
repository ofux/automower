package com.x.automower.parsing;

import com.x.automower.simulation.navigation.MoveForwardAction;
import com.x.automower.simulation.navigation.NavigationInstruction;
import com.x.automower.simulation.navigation.TurnLeftAction;
import com.x.automower.simulation.navigation.TurnRightAction;

import java.util.Optional;

public class NavigationInstructionParser implements Parser<NavigationInstruction> {
    @Override
    public NavigationInstruction deserialize(String s) throws ParsingException {
        var instruction = fromCode(s);
        if (instruction.isEmpty()) {
            throw new ParsingException(s, getClass());
        }

        return instruction.get();
    }

    private Optional<NavigationInstruction> fromCode(String code) {
        switch (code) {
            case "F":
                return Optional.of(new MoveForwardAction());
            case "L":
                return Optional.of(new TurnLeftAction());
            case "R":
                return Optional.of(new TurnRightAction());
            default:
                return Optional.empty();
        }
    }

    @Override
    public String serialize(NavigationInstruction instruction) {
        return instruction.getCode();
    }
}
