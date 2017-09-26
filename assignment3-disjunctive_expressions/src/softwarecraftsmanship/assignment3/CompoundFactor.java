package softwarecraftsmanship.assignment3;

import java.util.*;

public class CompoundFactor implements Factor {

    private final DisjunctiveExpression leftExpression;
    private final DisjunctiveExpression rightExpression;

    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public static final class Builder {
        public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            LinkedList<Token.Type> expectedOperatorSequence = new LinkedList<Token.Type>(Arrays.asList(
                    Token.Type.OPEN,
                    Token.Type.AND,
                    Token.Type.CLOSE
            ));

            ArrayList<DisjunctiveExpression> expressions = new ArrayList<DisjunctiveExpression>(2);
            Optional<LocationalToken> currentTokenOptional = Optional.of(token);
            for(int expectedTypeIndex=0; expectedTypeIndex<expectedOperatorSequence.size(); expectedTypeIndex++) {
                LocationalToken currentToken = currentTokenOptional.get();
                if(expectedTypeIndex%2 == 0) {
                    ParserException.verify(expectedOperatorSequence.removeFirst(), currentToken);
                }
                else {
                    expressions.add(DisjunctiveExpression.Builder.build(currentToken, lexer));
                }
            }
            
            return buildFromList(expressions);
        }

        //TODO: is there a better way to do this that doesn't involve directly referencing list indices?
        private static final CompoundFactor buildFromList(List<DisjunctiveExpression> expressions)
                throws ParserException {
            if(expressions.size() != 2) { throw new ParserException(ParserException.ErrorCode.ID_EXPECTED); }
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
