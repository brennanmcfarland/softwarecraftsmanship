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
            short expressionCount = 2;
            LinkedList<Token.Type> expectedOperatorSequence = new LinkedList<Token.Type>(Arrays.asList(
                    Token.Type.OPEN,
                    Token.Type.AND,
                    Token.Type.CLOSE
            ));

            ArrayList<DisjunctiveExpression> expressions = new ArrayList<DisjunctiveExpression>(expressionCount);
            Optional<LocationalToken> currentTokenOptional = Optional.of(token);
            for(int expectedTypeIndex=0; expectedTypeIndex<=expectedOperatorSequence.size()+expressionCount;
                expectedTypeIndex++) {
                LocationalToken currentToken = currentTokenOptional.get();
                if(expectedTypeIndex%expressionCount == 0) {
                    ParserException.verify(expectedOperatorSequence.removeFirst(), currentToken);
                }
                else {
                    expressions.add(DisjunctiveExpression.Builder.build(currentToken, lexer));
                }
                currentTokenOptional = lexer.nextValid();
            }
            return buildFromList(expressions, expressionCount);
        }

        private static final CompoundFactor buildFromList(List<DisjunctiveExpression> expressions,
                                                          short expressionCount) throws ParserException {
            if(expressions.size() != expressionCount) {
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
