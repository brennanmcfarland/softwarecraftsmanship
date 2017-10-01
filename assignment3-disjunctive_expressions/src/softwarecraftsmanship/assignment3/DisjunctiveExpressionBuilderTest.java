package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjunctiveExpressionBuilderTest {

    @Test
    void build() throws ParserException {
        buildCase("bread and butter");
        buildCase("beans and bacon");
        buildCase("(this and that)");
        buildCase("not (this and that)");
        Assertions.assertThrows(ParserException.class, () -> buildCase("or that"));
        Assertions.assertThrows(ParserException.class, () -> buildCase("not (this or that)"));
    }

    void buildCase(String input) throws ParserException {
        DisjunctiveLexer testLexer = new DisjunctiveLexer(input);
        LocationalToken testToken = testLexer.nextValid().get();
        DisjunctiveExpression testDisjunctiveExpression = DisjunctiveExpression.Builder.build(
                testToken, testLexer);
    }

}