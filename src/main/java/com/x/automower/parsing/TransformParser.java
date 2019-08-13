package com.x.automower.parsing;

import com.x.automower.simulation.math.Transform;

import javax.inject.Inject;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class TransformParser implements Parser<Transform> {

    private final Pattern pattern = compile("^(\\d+ \\d+) ([NEWS])$");
    private final Vector2DParser vector2DParser;
    private final OrientationParser orientationParser;

    @Inject
    public TransformParser(Vector2DParser vector2DParser, OrientationParser orientationParser) {
        this.vector2DParser = vector2DParser;
        this.orientationParser = orientationParser;
    }

    @Override
    public Transform deserialize(String s) throws ParsingException {
        var matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            throw new ParsingException(s, Transform.class);
        }

        return new Transform(vector2DParser.deserialize(matcher.group(1)), orientationParser.deserialize(matcher.group(2)));
    }

    @Override
    public String serialize(Transform transform) {
        return String.format("%s %s", vector2DParser.serialize(transform.getPosition()), orientationParser.serialize(transform.getOrientation()));
    }
}
