package softwarecraftsmanship.assignment3;

final class ConjunctiveRepresentation {

    private final String representation;
    private final boolean negation;

    public ConjunctiveRepresentation(String representation, boolean negation) {
        this.representation = representation;
        this.negation = negation;
    }

    final String getRepresentation() { return representation; }

    final boolean getNegation() { return negation; }

    @Override
    public String toString() {
        if(negation) {
            return Token.Type.NOT.getPattern() + " " + representation;
        }
        else {
            return representation;
        }
    }
}
