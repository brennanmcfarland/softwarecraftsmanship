package com.company;

import jdk.nashorn.internal.parser.TokenType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TokenTest {

    private static class TestToken {

        private static Token testTokenInstance = null;
        //if values not given, these hold the defaults from the last created token
        private static Token.Type defaultCorrectType = Token.Type.NOT;
        private static String defaultCorrectData = "testdata";

        //returns the test token or creates a new one if DNE
        static Token getTestToken() {
            if(testTokenInstance == null) {
                createTestToken(defaultCorrectType, defaultCorrectData);
            }
            return testTokenInstance;
        }

        //force creation of a new test token
        static Token createTestToken(Token.Type correctType, String correctData) {
            defaultCorrectType = correctType;
            defaultCorrectData = correctData;
            try {
                Class tokenClass = Class.forName("com.company.Token");
                Method tokenOfMethod = tokenClass.getMethod("of", Token.Type.class, String.class);
                return testTokenInstance = (Token) tokenOfMethod.invoke(null, correctType, correctData);
            }
            catch (Exception e) {
                System.out.println("Unable to create test class");
                e.printStackTrace();
                return testTokenInstance; //attempt to continue with the last successfully created Token
            }
        }
    }

    @org.junit.jupiter.api.Test
    void getType() {
        getTypeTestCase(Token.Type.NOT, "testdata");
        getTypeTestCase(null, "testdata");
        getTypeTestCase(Token.Type.AND, null);
        getTypeTestCase(null, null);
    }

    void getTypeTestCase(Token.Type correctType, String correctData) {
        TestToken.createTestToken(correctType, correctData);
        Assertions.assertEquals(correctType, TestToken.getTestToken().getType());
        System.out.println("passed test case {" + correctType + ", " + correctData + "}");
    }

    @org.junit.jupiter.api.Test
    void getData() {
    }

    @org.junit.jupiter.api.Test
    void of() {
    }

    @org.junit.jupiter.api.Test
    void equals() {
    }

    //different from naming convention because conflicts with object.toString() return type otherwise
    @org.junit.jupiter.api.Test
    public void testToString() {
    }

}