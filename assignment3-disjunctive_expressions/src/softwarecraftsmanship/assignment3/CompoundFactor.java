package softwarecraftsmanship.assignment3;

import javax.xml.stream.Location;
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
            //so iterate over the tokens to get the two expressions and verify the correct operators from the above list
            ArrayList<DisjunctiveExpression> expressions = new ArrayList<DisjunctiveExpression>(EXPRESSIONCOUNT);
            Optional<LocationalToken> currentTokenOptional = Optional.of(token);
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
            return buildFromList(expressions);
        }

        private static final CompoundFactor buildFromList(List<DisjunctiveExpression> expressions)
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
