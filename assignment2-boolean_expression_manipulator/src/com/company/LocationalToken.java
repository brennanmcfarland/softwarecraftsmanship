package com.company;

public final class LocationalToken {

    private final Token token;
    private final int location;

    public LocationalToken(Token token, int location) {
        this.token = token;
        this.location = location;
    }

    //TODO: delegate to Token object methods to get token type and ancillary data?
    //TODO: refactor and unit tests

    public Token getToken() {
        return token;
    }

    public int getLocation() {
        return location;
    }
}
