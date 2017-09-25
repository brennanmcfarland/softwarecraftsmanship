package softwarecraftsmanship.assignment3;

import java.util.Hashtable;
import java.util.Optional;
import java.util.Map;

public final class Token {
    public enum Type {

        NOT("not", false, false, Optional.empty()),
        AND("and", false, true, Optional.of(ParserException.ErrorCode.AND_EXPECTED)),
        OR("or", false, true, Optional.empty()),
        OPEN("\\(", false, false, Optional.of(ParserException.ErrorCode.OPEN_EXPECTED)),
        CLOSE("\\)", false, false, Optional.of(ParserException.ErrorCode.CLOSE_EXPECTED)),
        ID("[a-z]+", true, false, Optional.of(ParserException.ErrorCode.ID_EXPECTED)),
        NUMBER("\\-?[0-9]+", true, false, Optional.empty()),
        BINARYOP("\\+|-|\\*|/", true, false, Optional.empty()),
        WHITESPACE("\\s+", false, false, Optional.empty());

        private final String pattern; //the token's regex pattern
        private final Boolean hasData; //whether the token has additional associated data
        private boolean isComplex;
        private Optional<ParserException.ErrorCode> errorCode;

        Type(String pattern, Boolean hasData, boolean isComplex, Optional<ParserException.ErrorCode> errorCode) {
            this.pattern = pattern;
            this.hasData = hasData;
            this.isComplex = isComplex;
            this.errorCode = errorCode;
        }

        public String getPattern() {
            return pattern;
        }

        public Boolean getHasData() {
            return hasData;
        }

        public boolean getIsComplex() { return isComplex; }

        public Optional<ParserException.ErrorCode> getErrorCode() { return errorCode; }
    }

    //for building new instances of Token
    private static class Builder {

        private final Type type;
        private final Optional<String> data;

        public Builder(Type type, Optional<String> data) {
            this.type = type;
            this.data = data;
        }

        private Token build() {
            return new Token(type, data);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            Builder builder = (Builder)o;

            if (type != builder.type) { return false; }
            return data != null ? data.equals(builder.data) : builder.data == null;
        }

        @Override
        public int hashCode() {
            int result = type != null ? type.hashCode() : 0;
            result = 31 * result + (data != null ? data.hashCode() : 0);
            return result;
        }
    }

    private final Type type;
    private final Optional<String> data;
    private static Map<Builder, Token> tokenMap = new Hashtable<Builder, Token>(); //maps builders to associated tokens

    private Token(Type type, Optional<String> data) {
        this.type = type;
        this.data = data;
    }

    public static Token of(Type type, String data) {

        //guard clause; if the appropriate builder doesn't exist, create it and return the build
        Builder requestedBuilder = new Builder(type, Optional.ofNullable(data));
        if(!tokenMap.containsKey(requestedBuilder)) {
            tokenMap.put(requestedBuilder, requestedBuilder.build());
        }

        //nominal case; if the builder and token match, return the token
        return tokenMap.get(requestedBuilder);
    }


    public Type getType() { return type; }

    public Optional<String> getData() { return data; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) { return false; }

        Token token = (Token) o;

        if (type != token.type) { return false; }
        return data != null ? data.equals(token.data) : token.data == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}
