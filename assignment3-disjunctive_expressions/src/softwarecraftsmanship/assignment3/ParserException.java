package softwarecraftsmanship.assignment3;

import javax.xml.stream.Location;
import java.util.Optional;

public final class ParserException extends Exception {

    static long serialVersionUID = 293;

    public enum ErrorCode {
        TOKEN_EXPECTED,
        INVALID_TOKEN,
        TRAILING_INPUT,
        AND_EXPECTED,
        OPEN_EXPECTED,
        CLOSE_EXPECTED,
        ID_EXPECTED
    }

    private final ErrorCode errorCode;
    private final int location;

    public ErrorCode getErrorCode() { return errorCode; }

    public int getLocation() { return location; }

    public ParserException(LocationalToken token, ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.location = token.getLocation();
    }

    public ParserException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.location = -1;
    }

    public static void verify(Optional<LocationalToken> token) throws ParserException {
        if(!token.isPresent()) {
            throw new ParserException(ErrorCode.TOKEN_EXPECTED);
        }
    }

    public final static void verify(Token.Type expectedType, LocationalToken token) throws ParserException {
        if(token.getTokenType() == null || !token.getTokenType().equals(expectedType)) {
            Optional<ErrorCode> errorCode = expectedType.getErrorCode();
            if(errorCode.isPresent()) {
                throw new ParserException(token, expectedType.getErrorCode().get());
            }
            throw new ParserException(token, ErrorCode.TOKEN_EXPECTED);
        }
    }
    public static void verifyEnd(Optional<LocationalToken> token) throws ParserException {
        if(token.isPresent()) {
            throw new ParserException(token.get(), ErrorCode.TRAILING_INPUT);
        }
    }

    @Override
    public String toString() {
        return "ParserException{" +
                "serialVersionUID=" + serialVersionUID +
                ", errorCode=" + errorCode +
                ", location=" + location +
                '}';
    }
}
