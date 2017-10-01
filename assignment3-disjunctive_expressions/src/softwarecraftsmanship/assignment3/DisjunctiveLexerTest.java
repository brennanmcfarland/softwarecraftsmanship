package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Optional;

class DisjunctiveLexerTest {

    @Test
    void nextValid() throws ParserException {
        nextValidCase("");
        nextValidCase("and");
        nextValidCase("and what");
        Assertions.assertThrows(ParserException.class, () -> nextValidCase("this or that"));
        Assertions.assertThrows(ParserException.class, () -> nextValidCase("(one and 3)"));
    }

    void nextValidCase(String input) throws ParserException {
        Lexer testLexer = new Lexer(input);
        DisjunctiveLexer testDisjunctiveLexer = new DisjunctiveLexer(input);
        while(true) {
            Optional<LocationalToken> nextToken = testLexer.nextValid(
                    DisjunctiveLexer.validTypes, DisjunctiveLexer.invalidTypes);
            if (nextToken.equals(Optional.empty())) {
                return;
            }
            Assertions.assertEquals(nextToken.get().getTokenType(),
                    testDisjunctiveLexer.nextValid().get().getTokenType());
        }
    }
}