package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CompoundFactorBuilderTest {

    @Test
    void build() throws ParserException {
        buildCase("(this and that)");
        buildCase("(this and not that)");
        buildCase("((a and b) and not c)");
        Assertions.assertThrows(ParserException.class, () -> buildCase("((a and b) or c)"));
        Assertions.assertThrows(ParserException.class, () -> buildCase("(a and)"));
        Assertions.assertThrows(ParserException.class, () -> buildCase("(a and b or c"));
        Assertions.assertThrows(ParserException.class, () -> buildCase("a"));
        Assertions.assertThrows(ParserException.class, () -> buildCase("((a or b) and c"));
    }

    void buildCase(String input) throws ParserException {
        DisjunctiveLexer testLexer = new DisjunctiveLexer(input);
        LocationalToken testToken = testLexer.nextValid().get();
        CompoundFactor testCompoundFactor = CompoundFactor.Builder.build(testToken, testLexer);
        System.out.println(testCompoundFactor.toString());
    }

}