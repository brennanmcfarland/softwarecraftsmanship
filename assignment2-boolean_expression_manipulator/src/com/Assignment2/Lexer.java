package com.company;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//TODO: refactor, write unit tests and TEST IT
public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher tokenMatcher;

    public Lexer(String input) {
        tokenMatcher = tokenPatterns.matcher(input);
    }

    //TODO: refactor for readability
    static {
        Token.Type[] types = Token.Type.values();
        StringBuilder typesRegexString = new StringBuilder();
        for(type : types) {
            typesRegexString.append(type.getPattern());
        }
        tokenPatterns = Pattern.compile(typesRegexString.toString());
    }

    //also moves the matcher index forward if it is not at the end
    public Boolean hasNext() {
        return tokenMatcher.find();
    }

    public LocationalToken next() throws ParserException {
        String nextTokenString = null;
        Token.Type[] types = Token.Type.values();
        for(type : types) {
            nextTokenString = tokenMatcher.group(type.getPattern());
            if(nextTokenString != null) {
                Token.Type nextTokenType = new Token.Type(nextTokenString, type.getHasData(), type.getIsComplex());
                Token nextToken = Token.of(nextTokenType, nextTokenString);
                return new LocationalToken(nextToken, tokenMatcher.start());
            }
        }
        throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
    }

    //TODO: refactor for readability
    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException {

        if(!hasNext()) { return Optional.empty(); }
        LocationalToken nextToken;
        while(validTypes.contains(nextToken.getTokenType()) || invalidTypes.contains(nextToken.getTokenType())) {
            nextToken = next();
        }
        if(invalidTypes.contains(nextToken.getTokenType())) {
            throw new ParserException(nextToken, ParserException.ErrorCode.INVALID_TOKEN);
        }

        //otherwise it is a valid token
        return Optional.of(nextToken);
    }

}