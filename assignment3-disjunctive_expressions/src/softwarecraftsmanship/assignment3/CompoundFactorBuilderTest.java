package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Test;


class CompoundFactorBuilderTest {

    @Test
    void build() throws ParserException {
        DisjunctiveLexer testLexer = new DisjunctiveLexer("(bread and butter)");
        LocationalToken testToken = testLexer.nextValid().get();
        CompoundFactor testCompoundFactor = CompoundFactor.Builder.build(testToken, testLexer);
        System.out.println(testCompoundFactor.toString());
    }

}