package softwarecraftsmanship.assignment3;

import java.util.*;

public class CompoundFactor implements Factor {

    private final Identifier leftExpression;
    private final Identifier rightExpression;

    private CompoundFactor(Identifier leftExpression, Identifier rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    public static final class Builder {
        public static final CompoundFactor build(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            LinkedList<Token.Type> expectedTypeSequence = new LinkedList<Token.Type>(Arrays.asList(
                    Token.Type.OPEN,
                    Token.Type.ID,
                    Token.Type.AND,
                    Token.Type.ID,
                    Token.Type.CLOSE
            ));
            ArrayList<Identifier> expressions = new ArrayList<Identifier>(2);
            Optional<LocationalToken> currentTokenOptional = Optional.of(token);
            do {
                LocationalToken currentToken = currentTokenOptional.get();
                ParserException.verify(expectedTypeSequence.removeFirst(), currentToken);
                if(currentToken.getToken().getType().equals(Token.Type.ID)) {
                    expressions.add(Identifier.Builder.build(currentToken));
                }
            } while(!(currentTokenOptional = lexer.nextValid()).equals(Optional.empty()));
            return buildFromList(expressions);
        }

        //TODO: is there a better way to do this that doesn't involve directly referencing list indices?
        private static final CompoundFactor buildFromList(List<Identifier> expressions) throws ParserException {
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
