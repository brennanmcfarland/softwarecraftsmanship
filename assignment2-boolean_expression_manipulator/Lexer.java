package com.company;

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
        StringBuilder typesRegexString = new StringBuilder();
        for(Token.Type type : Token.Type.values()) {
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
        for(Token.Type type : Token.Type.values()) {
            nextTokenString = tokenMatcher.group(type.getPattern());
            if(nextTokenString != null) {
                Token nextToken = Token.of(type, nextTokenString);
                return new LocationalToken(nextToken, tokenMatcher.start());
            }
        }
        throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException {

        if(!hasNext()) { return Optional.empty(); }
        LocationalToken nextToken;
        do {
            nextToken = next();
        } while(validTypes.contains(nextToken.getTokenType()) || invalidTypes.contains(nextToken.getTokenType()));
        if(invalidTypes.contains(nextToken.getTokenType())) {
            throw new ParserException(nextToken, ParserException.ErrorCode.INVALID_TOKEN);
        }

        //otherwise it is a valid token
        return Optional.of(nextToken);
    }

}