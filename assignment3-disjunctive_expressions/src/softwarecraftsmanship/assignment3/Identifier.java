package softwarecraftsmanship.assignment3;

public final class Identifier {

    private final String id;

    private Identifier(String id) {
        this.id = id;
    }

    public static final class Builder {
        public static final Identifier build(LocationalToken token) throws ParserException {
            if(!token.getToken().getType().equals(Token.Type.ID)) {
                throw new ParserException(token, ParserException.ErrorCode.ID_EXPECTED);
            }
            //TODO: return the corresponding identifier
            return null;
        }
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "id='" + id + '\'' +
                '}';
    }
}
