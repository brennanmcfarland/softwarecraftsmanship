package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocationalTokenTest {

    @Test
    void testLocationalToken() {
        locationalTokenTestCase(null, 32);
        locationalTokenTestCase(Token.of(Token.Type.BINARYOP, "test data"), 0);
        System.out.println("Passed unit tests for testLocationalToken.");
    }

    void locationalTokenTestCase(Token testToken, int testLocation) {
        LocationalToken testLocationalToken = new LocationalToken(testToken, testLocation);
        Assertions.assertEquals(testToken, testLocationalToken.getToken());
        Assertions.assertEquals(testLocation, testLocationalToken.getLocation());
        if(testToken != null) {
            Assertions.assertEquals(testToken.getType(), testLocationalToken.getTokenType());
            Assertions.assertEquals(testToken.getData(), testLocationalToken.getTokenData());
        }
    }
}