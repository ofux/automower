package com.x.automower.parsing;

public class ParsingException extends Exception {
    public ParsingException(String parsedString, Class clazz) {
        super(String.format("Failed to parse '%s' as %s", parsedString, clazz.getName()));
    }
}
