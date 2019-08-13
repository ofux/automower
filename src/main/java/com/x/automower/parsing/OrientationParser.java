package com.x.automower.parsing;

import com.x.automower.simulation.math.Orientation;

import java.util.Arrays;
import java.util.Optional;

public class OrientationParser implements Parser<Orientation> {

    @Override
    public Orientation deserialize(String s) throws ParsingException {
        var orientation = fromCode(s);
        if (orientation.isEmpty()) {
            throw new ParsingException(s, Orientation.class);
        }

        return orientation.get();
    }

    private Optional<Orientation> fromCode(String code) {
        return Arrays.stream(Orientation.values())
                .filter(orientation -> orientation.getCode().equals(code))
                .findFirst();
    }

    @Override
    public String serialize(Orientation orientation) {
        return orientation.getCode();
    }
}
