package com.x.automower.parsing;

public interface Parser<T> {
    T deserialize(String s) throws ParsingException;
    String serialize(T obj);
}
