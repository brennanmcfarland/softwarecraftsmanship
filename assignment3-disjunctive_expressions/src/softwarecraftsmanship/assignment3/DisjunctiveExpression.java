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

    //gets the conjunctive representation, removing duplicate negatives if necessary
    public final String conjunctiveRepresentation() {
        ConjunctiveRepresentation factorConjunctiveRepresentation = factor.conjunctiveRepresentation();
        StringBuilder conjunctiveRepresentationString = new StringBuilder();
        //conjunctive is only negative if this is positive and the conjunctive representation is negative or vice versa
        if(positive != factorConjunctiveRepresentation.getNegation()) {
            conjunctiveRepresentationString.append(Token.Type.NOT.getPattern() + " ");
        }
        conjunctiveRepresentationString.append(factorConjunctiveRepresentation.getRepresentation());
        return conjunctiveRepresentationString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DisjunctiveExpression that = (DisjunctiveExpression) o;

        if (positive != that.positive) return false;
        return factor != null ? factor.equals(that.factor) : that.factor == null;
    }

    @Override
    public int hashCode() {
        int result = factor != null ? factor.hashCode() : 0;
        result = 31 * result + (positive ? 1 : 0);
        return result;
    }
}
