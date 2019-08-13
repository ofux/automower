package com.x.automower.parsing;

import com.x.automower.simulation.math.Vector2D;

import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

public class Vector2DParser implements Parser<Vector2D> {

    private final Pattern pattern = compile("^(\\d+) (\\d+)$");

    @Override
    public Vector2D deserialize(String s) throws ParsingException {
        var matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            throw new ParsingException(s, Vector2D.class);
        }

        return new Vector2D(parseInt(matcher.group(1)), parseInt(matcher.group(2)));
    }

    @Override
    public String serialize(Vector2D vector2D) {
        return String.format("%d %d", vector2D.getX(), vector2D.getY());
    }
}
