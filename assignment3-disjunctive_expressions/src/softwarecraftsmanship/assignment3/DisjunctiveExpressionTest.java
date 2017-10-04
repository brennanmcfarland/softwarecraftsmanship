package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DisjunctiveExpressionTest {
    @Test
    void negate() throws ParserException {
        negateCase("this", "(this and that)");
        negateCase("not", "not (this and that)");
        negateCase("not", "not this");
    }

    void negateCase(String tokenId, String lexerInput) throws ParserException {
        DisjunctiveExpression testExpression = DisjunctiveExpression.Builder.build(
                new LocationalToken(Token.of(Token.Type.ID, tokenId), 0), new DisjunctiveLexer(lexerInput));
        DisjunctiveExpression testExpressionNegation = testExpression.negate();
        Assertions.assertNotEquals(testExpression, testExpressionNegation);
    }

    @Test
    void conjunctiveRepresentation() throws ParserException {
        conjunctiveRepresentationCase("(", "this and that)", "not (not this or not that)");
    }

    void conjunctiveRepresentationCase(String tokenId, String lexerInput, String correctOutput) throws ParserException {
        Token firstToken = Token.of(Token.Type.ID, tokenId);
        if(tokenId.equals("(")) {
            firstToken = Token.of(Token.Type.OPEN, tokenId);
        }
        else if(tokenId.equals("not")) {
            firstToken = Token.of(Token.Type.NOT, tokenId);
        }
        DisjunctiveExpression testExpression = DisjunctiveExpression.Builder.build(
                new LocationalToken(firstToken, 0), new DisjunctiveLexer(lexerInput));
        Assertions.assertTrue(testExpression.conjunctiveRepresentation().equals(correctOutput)
            || ("not " + testExpression.conjunctiveRepresentation()).equals(correctOutput));
    }
}