package com.company;

import java.util.Optional;

public final class ParserException extends Exception {

    long serialVersionUID = 293;

    public enum ErrorCode {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT;
    }

    private final ErrorCode errorCode;
    private final int location;

    public ErrorCode getErrorCode() { return errorCode; }

    public int getLocation() { return location; }

    public ParserException(LocationalToken token, ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.location = token.getLocation();
    }

    public ParserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.location = -1;
    }

    public static void verify(Optional<LocationalToken> token) throws ParserException {
        if(!token.isPresent()) {
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
        }
    }

    public static void verifyEnd(Optional<LocationalToken> token) throws ParserException {
        if(token.isPresent()) {
            throw new ParserException(token.get(), ErrorCode.TRAILING_INPUT);
        }
    }

    @Override
    public String toString() {
        return "ParserException{" +
                "serialVersionUID=" + serialVersionUID +
                ", errorCode=" + errorCode +
                ", location=" + location +
                '}';
    }
}
