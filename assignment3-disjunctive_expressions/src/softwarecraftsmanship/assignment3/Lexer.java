package softwarecraftsmanship.assignment3;


import javax.swing.text.html.Option;
import javax.xml.stream.Location;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional;
import java.util.Set;

public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher tokenMatcher;

    public Lexer(String input) {
        if(input == null) { input = ""; }
        tokenMatcher = tokenPatterns.matcher(input);
    }

    static {
        StringBuilder typesRegexString = new StringBuilder();
        for(Token.Type type : Token.Type.values()) {
            typesRegexString.append("(?<" + type.name() + ">" + type.getPattern() + ")|");
        }
        //we truncate the trailing | symbol of the string builder
        tokenPatterns = Pattern.compile(typesRegexString.substring(0,typesRegexString.length()-1));
    }

    //also moves the matcher index forward if it is not at the end
    public Boolean hasNext() {
        return tokenMatcher.find();
    }

    public LocationalToken next() throws ParserException {
        String nextTokenString = null;
        for(Token.Type type : Token.Type.values()) {
            nextTokenString = tokenMatcher.group(type.name());
            if(nextTokenString != null) {
                Token nextToken = Token.of(type, nextTokenString);
                return new LocationalToken(nextToken, tokenMatcher.start());
            }
        }
        throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes)
            throws ParserException {
        LocationalToken nextToken;
        while(hasNext()) {
            nextToken = next();
            if(invalidTypes.contains(nextToken.getTokenType())) {
                throw new ParserException(nextToken, ParserException.ErrorCode.INVALID_TOKEN);
            }
            else if(validTypes.contains(nextToken.getTokenType())) {
                System.out.println("returning " + nextToken.getToken().getType());
                return Optional.of(nextToken);
            }
        }
        return Optional.empty();
    }
}
