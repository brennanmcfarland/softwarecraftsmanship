package com.company;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher tokenMatcher;

    public Lexer(String input) {
        tokenMatcher = tokenPatterns.matcher(input);
    }

    static {
        tokenPatterns = Pattern.compile("the regexes from Token.Types"); //TODO: fix this
    }

    public Boolean hasNext() {
        //find keeps track of the where it left off, so no need to save the index
        return tokenMatcher.find();
    }

    public LocationalToken next() throws ParserException {
        if(!hasNext()) { throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED); }
        String nextTokenString = tokenMatcher.group();
        Token.Type nextTokenType; //TODO: set this based on nextTokenString
        String nextTokenData; //TODO: is this necessary?  if not don't initialize with it
        Token nextToken = Token.of(nextTokenType, nextTokenData);
        return new LocationalToken(nextToken, tokenMatcher.start()); //TODO: is this what the assignment means?
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