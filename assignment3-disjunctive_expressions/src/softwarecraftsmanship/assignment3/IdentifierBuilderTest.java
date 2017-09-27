package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentifierBuilderTest {

    @Test
    void build() throws ParserException {
        Assertions.assertThrows(ParserException.class, () -> Identifier.Builder.build(
                new LocationalToken(Token.of(Token.Type.WHITESPACE, null), 0)));
        Assertions.assertThrows(ParserException.class, () -> Identifier.Builder.build(
                new LocationalToken(Token.of(Token.Type.AND, null), 0)));
        Identifier.Builder.build(new LocationalToken(Token.of(Token.Type.ID, ""), 0));
        Identifier.Builder.build(new LocationalToken(Token.of(Token.Type.ID, "blip"), 0));
    }
}