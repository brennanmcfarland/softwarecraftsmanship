package softwarecraftsmanship.assignment3;

import java.util.*;

public class CompoundFactor implements Factor {

    public static final short EXPRESSIONCOUNT = 2; //the (not recursive) number of expressions in a compound factor
    private final DisjunctiveExpression leftExpression;
    private final DisjunctiveExpression rightExpression;

    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public static final class Builder {
        public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            //every compound factor should have an open, and and close, with ids in between
            LinkedList<Token.Type> expectedOperatorSequence = new LinkedList<Token.Type>(Arrays.asList(
                    Token.Type.OPEN,
                    Token.Type.AND,
                    Token.Type.CLOSE
            ));
            return buildFromTypeList(expectedOperatorSequence, token, lexer);
        }

        private static final CompoundFactor buildFromTypeList(LinkedList<Token.Type> expectedOperatorSequence,
                                                              LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {
            ArrayList<DisjunctiveExpression> expressions = new ArrayList<DisjunctiveExpression>(EXPRESSIONCOUNT);
            Optional<LocationalToken> currentTokenOptional = Optional.of(token);
            //iterate over the tokens to get the two expressions' expressions and operators
            for(int expectedTypeIndex=0; expectedTypeIndex<=expectedOperatorSequence.size()+EXPRESSIONCOUNT;
                expectedTypeIndex++) {
                ParserException.verify(currentTokenOptional);
                LocationalToken currentToken = currentTokenOptional.get();
                //every odd operator/expression should be an operator, so verify it is the right type
                if(expectedTypeIndex % EXPRESSIONCOUNT == 0) {
                    ParserException.verify(expectedOperatorSequence.removeFirst(), currentToken);
                }
                //every even operator/expression should be an expression, so build it
                else {
                    expressions.add(DisjunctiveExpression.Builder.build(currentToken, lexer));
                }
                currentTokenOptional = lexer.nextValid();
            }
            return buildFromExpressionList(expressions);
        }

        private static final CompoundFactor buildFromExpressionList(List<DisjunctiveExpression> expressions)
                throws ParserException {
            if(expressions.size() != EXPRESSIONCOUNT) {
                throw new ParserException(ParserException.ErrorCode.ID_EXPECTED);
            }
            return new CompoundFactor(expressions.get(0), expressions.get(1));
        }
    }

    @Override
    public String toString() {
        return "CompoundFactor{" +
                "leftExpression=" + leftExpression +
                ", rightExpression=" + rightExpression +
                '}';
    }
}
