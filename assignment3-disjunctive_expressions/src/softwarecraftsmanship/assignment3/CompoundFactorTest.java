package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompoundFactorTest {
    @Test
    void conjunctiveRepresentation() throws ParserException {
        conjunctiveRepresentationCase("this and that)", "(not this or not that)");
        conjunctiveRepresentationCase("not this and not that)", "(this or that)");
    }

    void conjunctiveRepresentationCase(String input, String conjunctiveRepresentation) throws ParserException {
        CompoundFactor testCompoundFactor = CompoundFactor.Builder.build(
                new LocationalToken(Token.of(Token.Type.OPEN, null), 0), new DisjunctiveLexer(input));
        Assertions.assertEquals(conjunctiveRepresentation,
                testCompoundFactor.conjunctiveRepresentation().getRepresentation());
        Assertions.assertTrue(testCompoundFactor.conjunctiveRepresentation().getNegation());
    }
}