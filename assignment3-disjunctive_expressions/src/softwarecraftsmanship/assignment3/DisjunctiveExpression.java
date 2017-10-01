package softwarecraftsmanship.assignment3;

import java.util.Optional;

public final class DisjunctiveExpression {

    private final Factor factor;
    private final boolean positive;

    private DisjunctiveExpression(Factor factor, boolean positive) {
        this.factor = factor;
        this.positive = positive;
    }

    public static final class Builder {
        public static final DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {
            //process optional NOT
            boolean positive = true;
            if(token.getTokenType().equals(Token.Type.NOT)) {
                positive = false;
                Optional<LocationalToken> nextTokenOptional = lexer.nextValid();
                ParserException.verify(nextTokenOptional);
                token = nextTokenOptional.get();
            }
            //process the factor
            Factor factor = buildFactor(token, lexer);
            return new DisjunctiveExpression(factor, positive);
        }

        private static final Factor buildFactor(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            //if it's an identifier, try to build it as such, otherwise try to build it as a compound factor
            if(token.getTokenType().equals(Token.Type.ID)) {
                return Identifier.Builder.build(token);
            }
            else {
                return buildCompoundFactor(token, lexer);
            }
        }

        private static final Factor buildCompoundFactor(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException{
            return CompoundFactor.Builder.build(token, lexer);
        }
    }

    public final DisjunctiveExpression negate() {
        return new DisjunctiveExpression(factor, !positive);
    }
}
