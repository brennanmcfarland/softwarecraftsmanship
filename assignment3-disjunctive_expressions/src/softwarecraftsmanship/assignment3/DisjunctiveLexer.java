package softwarecraftsmanship.assignment3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class DisjunctiveLexer {

    public static final Set<Token.Type> validTypes = new HashSet(
            Arrays.asList(Token.Type.AND, Token.Type.ID, Token.Type.NOT, Token.Type.OPEN, Token.Type.CLOSE));
    public static final Set<Token.Type> invalidTypes = new HashSet(
            Arrays.asList(Token.Type.OR, Token.Type.NUMBER, Token.Type.BINARYOP));
    private Lexer lexer;

    public DisjunctiveLexer(String input) {
        lexer = new Lexer(input);
    }

    public Optional<LocationalToken> nextValid() throws ParserException {
        return lexer.nextValid(validTypes, invalidTypes);
    }
}
