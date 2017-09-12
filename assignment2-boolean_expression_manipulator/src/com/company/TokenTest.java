package com.company;

import org.junit.jupiter.api.Assertions;

import java.lang.reflect.Method;
import java.util.Optional;

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
        printTestCaseReport("getType", correctType, correctData);
    }

    @org.junit.jupiter.api.Test
    void getData() {
        getDataTestCase(Token.Type.OR, "more test data");
        getDataTestCase(Token.Type.ID, "   ");
        getDataTestCase(Token.Type.WHITESPACE, null);
    }

    void getDataTestCase(Token.Type correctType, String correctData) {
        TestToken.createTestToken(correctType, correctData);
        Assertions.assertEquals(Optional.ofNullable(correctData), TestToken.getTestToken().getData());
        printTestCaseReport("getData", correctType, correctData);
    }

    @org.junit.jupiter.api.Test
    void of() {
        ofTestCase(Token.Type.NOT, "some test data");
        ofTestCase(Token.Type.OPEN, "");
        ofTestCase(Token.Type.BINARYOP, null);
        ofTestCase(null, null);
    }

    void ofTestCase(Token.Type correctType, String correctData) {
        Token testToken = Token.of(correctType, correctData);
        Assertions.assertEquals(correctType, testToken.getType());
        printTestCaseReport("of", correctType, correctData);
    }

    void printTestCaseReport(String methodName, Token.Type correctType, String correctData) {
        System.out.println("Passed " + methodName + "() test case {" + correctType + ", " + correctData + "}");
    }
}