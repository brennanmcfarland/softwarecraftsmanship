package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdentifierTest {

    @Test
    void conjunctiveRepresentation() throws ParserException {
        conjunctiveRepresentationCase("yadda");
        conjunctiveRepresentationCase("");
        conjunctiveRepresentationCase("blip");
    }

    void conjunctiveRepresentationCase(String id) throws ParserException {
        Identifier testIdentifier =
                Identifier.Builder.build(new LocationalToken(Token.of(Token.Type.ID, id), 0));
        ConjunctiveRepresentation testConjunctiveRepresentation = testIdentifier.conjunctiveRepresentation();
        Assertions.assertEquals(id, testConjunctiveRepresentation.getRepresentation());
        Assertions.assertFalse(testConjunctiveRepresentation.getNegation());
    }

}