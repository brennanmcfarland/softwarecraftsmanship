package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserExceptionTest {
    @Test
    void verify() throws ParserException {
        verifyValidCase(Token.Type.ID);
        verifyValidCase(Token.Type.WHITESPACE);
        verifyValidCase(Token.Type.OPEN);
        verifyValidCase(Token.Type.AND);
        verifyInvalidCase(Token.Type.AND, Token.Type.OR);
        verifyInvalidCase(Token.Type.ID, Token.Type.WHITESPACE);
        verifyInvalidCase(Token.Type.OPEN, Token.Type.CLOSE);
    }

    void verifyValidCase(Token.Type type) throws ParserException {
        ParserException.verify(type, new LocationalToken(Token.of(type, null), 0));
    }

    void verifyInvalidCase(Token.Type typeA, Token.Type typeB) {
        Assertions.assertThrows(ParserException.class, () -> ParserException.verify(typeA, new LocationalToken(
                Token.of(typeB, null), 0
        )));
    }
}