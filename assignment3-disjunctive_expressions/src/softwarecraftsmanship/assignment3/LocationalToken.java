package softwarecraftsmanship.assignment3;

import java.util.Optional;

public final class LocationalToken {

    private final Token token;
    private final int location;

    public LocationalToken(Token token, int location) {
        this.token = token;
        this.location = location;
    }

    public Token getToken() {
        return token;
    }

    public int getLocation() {
        return location;
    }

    public Token.Type getTokenType() { return token != null ? token.getType() : null; }

    public Optional<String> getTokenData() { return token != null ? token.getData() : null; }
}
