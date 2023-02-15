package com.poliscrypts.tictactoekata.model.enumeration;

public enum Square {
    BLANK(" "),
    X("X"),
    O("O");

    private final String value;

    Square(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
