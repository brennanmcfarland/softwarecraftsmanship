package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjunctiveExpressionBuilderTest {

    @Test
    void build() throws ParserException {
        DisjunctiveLexer testLexer = new DisjunctiveLexer("(bread and butter)");
        LocationalToken testToken = testLexer.nextValid().get();
        DisjunctiveExpression testDisjunctiveExpression = DisjunctiveExpression.Builder.build(
                testToken, testLexer);
    }

}