package com.company;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;
import java.util.Map;
import java.util.Set;

public final class Token {

    public enum Type {

        NOT("not", false),
        AND("and", false),
        OR("or", false),
        OPEN("\\(", false),
        CLOSE("\\)", false),
        ID("[a-z]+", true),
        NUMBER("\\-?[0-9]", true),
        BINARYOP("\\+|-|\\*|/", true),
        WHITESPACE("\\s+", false);

        private final String pattern;
        private final Boolean hasData;

        Type(String pattern, Boolean hasData) {
            this.pattern = pattern;
            this.hasData = hasData;
        }

        public String getPattern() {
            return pattern;
        }

        public Boolean getHasData() {
            return hasData;
        }
    }

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

        //TODO: data is an optional, so fix it to not check for null & use optional syntax instead, in all instances
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            if (type != builder.type) return false;
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
    private static Map<Builder, Token> tokenMap;

    //TODO: type and data should acccept actual values
    private Token(Type type, Optional<String> data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getData() {
        return data;
    }

    public static Token of(Type type, String data) {
        //TODO:
        //return token with these params if one already exists,otherwise make a new one and return it
        //if token type doesn't support data, ignore it

        //guard clause; if the appropriate builder doesn't exist, create it and return the build
        Builder requestedBuilder = new Builder(type, Optional.ofNullable(data));
        if(!tokenMap.containsKey(requestedBuilder))
            tokenMap.put(requestedBuilder, requestedBuilder.build());

        //guard clause; if a builder exists but the token doesn't match, build it
        Token requestedToken = new Token(type, Optional.ofNullable(data));
        if(!tokenMap.get(requestedBuilder).equals(requestedToken))
            tokenMap.replace(requestedBuilder, requestedBuilder.build());

        //nominal case; if the builder and token match, return the token
        return tokenMap.get(requestedBuilder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (type != token.type) return false;
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
