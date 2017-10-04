package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConjunctiveRepresentationTest {

    @Test
    void getRepresentation() {
        getRepresentationCase(null, false);
        getRepresentationCase("", false);
        getRepresentationCase("(this or that)", false);
        getRepresentationCase("(not this or not that)", true);
        getRepresentationCase("this", false);
    }

    void getRepresentationCase(String representation, boolean negation) {
        ConjunctiveRepresentation testConjunctiveRepresentation
                = new ConjunctiveRepresentation(representation, negation);
        Assertions.assertEquals(representation, testConjunctiveRepresentation.getRepresentation());
    }

    @Test
    void getNegation() {
        getNegationCase(null, false);
        getNegationCase("", false);
        getNegationCase("(this or that)", false);
        getNegationCase("(not this or not that)", true);
        getNegationCase("this", false);
    }

    void getNegationCase(String representation, boolean negation) {
        ConjunctiveRepresentation testConjunctiveRepresentation
                = new ConjunctiveRepresentation(representation, negation);
        Assertions.assertEquals(negation, testConjunctiveRepresentation.getNegation());
    }

}