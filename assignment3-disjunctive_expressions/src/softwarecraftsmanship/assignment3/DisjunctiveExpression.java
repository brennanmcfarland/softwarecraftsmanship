package softwarecraftsmanship.assignment3;

import java.util.Optional;

public final class DisjunctiveExpression {

    private final Factor factor;
    private final boolean positive;

    private DisjunctiveExpression(Factor factor, boolean positive) {
        this.factor = factor;
        this.positive = positive;
    }

    //TODO: may need to break this up into separate methods
    public static final class Builder {
        public static final DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {
            //process optional NOT
            boolean positive = true;
            if(token.getToken().getType().equals(Token.Type.NOT)) {
                positive = false;
                Optional<LocationalToken> nextTokenOptional = lexer.nextValid();
                if(nextTokenOptional.equals(Optional.empty())) {
                    throw new ParserException(token, ParserException.ErrorCode.TOKEN_EXPECTED);
                }
                token = nextTokenOptional.get();
            }
            //TODO: process Factor (ID or CompoundFactor)
            //TODO: build and return the disjunctive expression
            //TODO: make sure there's no trailing tokens at the end
        }

    }

    public final DisjunctiveExpression negate() {
        return new DisjunctiveExpression(factor, !positive);
    }
}
