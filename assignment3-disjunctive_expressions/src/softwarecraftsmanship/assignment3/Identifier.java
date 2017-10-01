package softwarecraftsmanship.assignment3;

public final class Identifier implements Factor {

    private final String id;

    private Identifier(String id) {
        this.id = id;
    }

    public static final class Builder {
        public static final Identifier build(LocationalToken token) throws ParserException {
            ParserException.verify(Token.Type.ID, token);
            return new Identifier(token.getTokenData().get());
        }
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "id='" + id + '\'' +
                '}';
    }
}
