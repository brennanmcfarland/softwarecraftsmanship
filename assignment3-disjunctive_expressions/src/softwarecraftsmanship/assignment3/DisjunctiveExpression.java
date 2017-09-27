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
                if(nextTokenOptional.equals(Optional.empty())) {
                    throw new ParserException(token, ParserException.ErrorCode.TOKEN_EXPECTED);
                }
                token = nextTokenOptional.get();
            }
            //process the factor
            Factor factor = buildFactor(token, lexer);
            return new DisjunctiveExpression(factor, positive);
        }

        private static final Factor buildFactor(LocationalToken token, DisjunctiveLexer lexer) throws ParserException {
            //try to parse it as an identifier
            try{
                return Identifier.Builder.build(token);
            } catch (ParserException identifierParseException) {
                if(!identifierParseException.getErrorCode().equals(ParserException.ErrorCode.ID_EXPECTED)) {
                    throw identifierParseException;
                }
                //if that fails, it must be a compound factor or erroneous input
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
