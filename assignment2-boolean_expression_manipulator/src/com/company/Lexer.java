package com.company;

import javax.swing.text.html.Option;
import javax.xml.stream.Location;
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

    //TODO: refactor this for readability
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
