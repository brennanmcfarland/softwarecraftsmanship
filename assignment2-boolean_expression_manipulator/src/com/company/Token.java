package com.company;

import java.util.Optional;

public final class Token {

    public enum Type {
        NOT, AND, OR, OPEN, CLOSE, ID, NUMBER, BINARYOP, WHITESPACE;

        private String pattern;
        private Boolean hasData;

        Type() {

        }

        public String getPattern() {
            return pattern;
        }

        public Boolean getHasData() {
            return hasData;
        }
    }

    private final Type type;
    private final Optional<String> ancillaryData;

    //TODO: type and ancillaryData should acccept actual values
    public Token() {
        type = null;
        ancillaryData = null;
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getAncillaryData() {
        return ancillaryData;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
