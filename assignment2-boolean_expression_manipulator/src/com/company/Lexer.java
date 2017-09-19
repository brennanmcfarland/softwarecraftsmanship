package com.company;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

public class Lexer {

    //TODO: finish this part (with the Pattern, initializing statically?)
    private static Pattern tokenPatterns;

    public Lexer(String input) {
        //TODO: set the matcher
    }

    public Boolean hasNext() {
        //TODO: return true if the matcher finds the next input subsequence in tokenPatterns
    }

    public LocationalToken next() throws ParserException {
        //TODO: return the next locational token found in the input, or throw a
        //TODO: TOKEN_EXPECTED exception if no more tokens present
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException {
        //TODO: If the
        //TODO: method finds a token of an invalid type, it throws an INVALID_TOKEN
        //TODO: exception along with the invalid token. If the method finds a token
        //TODO: that is neither valid nor invalid, the method ignores it and keeps
        //TODO: scanning the input in search of the next token. For example, if
        //TODO: WHITESPACE is neither valid or invalid, all input white spaces would
        //TODO: be skipped. If the method reaches the end of input without finding
        //TODO: another valid token, an empty optional is returned.
    }
}
