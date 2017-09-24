package softwarecraftsmanship.assignment3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.*;

class LexerTest {

    String[] inputs;
    int[] correctOutputs;

    @Test
    void hasNext() {
        inputs = new String[]{
                null,
                "",
                "this",
                "this and that ",
                "this and that and me or you (with he and him) for 37 times."};
        correctOutputs = new int[]{
                0,
                0,
                1,
                6,
                29
        };
        for(int testCase=0; testCase<inputs.length; testCase++) {
            hasNextCase(inputs[testCase], correctOutputs[testCase]);
        }
    }

    void hasNextCase(String input, int tokenCount) {
        Lexer testLexer = new Lexer(input);
        for(int numberTokens=0; numberTokens<tokenCount; numberTokens++) {
            Assertions.assertTrue(testLexer.hasNext());
        }
        Assertions.assertTrue(!testLexer.hasNext());
    }

    @Test
    void next() {
        Lexer testLexer = new Lexer("this and (that or 3)");
        Token.Type[] correctTokenTypes = {Token.Type.ID, Token.Type.WHITESPACE, Token.Type.AND, Token.Type.WHITESPACE,
            Token.Type.OPEN, Token.Type.ID, Token.Type.WHITESPACE, Token.Type.OR, Token.Type.WHITESPACE,
            Token.Type.NUMBER, Token.Type.CLOSE};
        int[] correctTokenLocations = {0,4,5,8,9,10,14,15,17,18,19};
        ArrayList<LocationalToken> testLocationalTokens = new ArrayList<LocationalToken>();
        try{
            while(testLexer.hasNext())
            {
                testLocationalTokens.add(testLexer.next());
            }
        } catch(ParserException parserException) {
            throw new AssertionError();
        }

        for(int token=0; token<correctTokenTypes.length; token++) {
            Assertions.assertEquals(testLocationalTokens.get(token).getToken().getType(), correctTokenTypes[token]);
            Assertions.assertEquals(testLocationalTokens.get(token).getLocation(), correctTokenLocations[token]);
        }
    }

    @Test
    void nextValid() throws ParserException {
        HashSet<Token.Type> validTypes = new HashSet(Arrays.asList(Token.Type.values()));
        HashSet<Token.Type> invalidTypes = new HashSet<Token.Type>(Arrays.asList(new Token.Type[]{Token.Type.NUMBER}));
        Token.Type[] correctTypes = {Token.Type.ID, Token.Type.WHITESPACE, Token.Type.OR};
        nextValidCase("this or", validTypes, invalidTypes, correctTypes);
        Assertions.assertThrows(ParserException.class,
                () -> nextValidCase("and a 1 and a 2", validTypes, invalidTypes, correctTypes));
    }

    void nextValidCase(String input, Set<Token.Type> validTypes, Set<Token.Type> invalidTypes,
                       Token.Type[] correctTypes) throws ParserException {
        Lexer testLexer = new Lexer(input);
        ArrayList<LocationalToken> testValidTokens = new ArrayList<LocationalToken>();

        while(true) {
            Optional<LocationalToken> nextValidToken = testLexer.nextValid(validTypes, invalidTypes);
            if(nextValidToken.equals(Optional.empty())) { break; }
            testValidTokens.add(nextValidToken.get());
        }

        for(int token=0; token<correctTypes.length; token++) {
            Assertions.assertEquals(testValidTokens.get(token).getToken().getType(), correctTypes[token]);
            System.out.println(testValidTokens.get(token).getToken().getType() + " " + correctTypes[token]);
        }
    }

    @Test
    void estimateComplexity() {
        inputs = new String[]{
                null,
                "",
                "this ",
                "this and that",
                "that or this",
                "(this or that) and not mine",
                "this and (that or mine) and yours for 3"};
        correctOutputs = new int[]{
                0,
                0,
                0,
                1,
                1,
                2,
                3
        };
        for(int testCase=0; testCase<inputs.length; testCase++) {
            estimateComplexityCase(inputs[testCase], correctOutputs[testCase]);
        }
    }

    void estimateComplexityCase(String input, int correctOutput) {
        int complexity = 0;
        Lexer testLexer = new Lexer(input);
        Optional<LocationalToken> nextValidToken;
        try {
            while(true) {
                nextValidToken = testLexer.nextValid(new HashSet(Arrays.asList(Token.Type.values())),
                        new HashSet());
                if(nextValidToken.equals(Optional.empty())) { break; }
                if(nextValidToken.get().getToken().getType().getIsComplex()) { complexity++; }
            }
        } catch (ParserException parserException) {
            throw new AssertionError();
        }
        Assertions.assertEquals(complexity, correctOutput);
    }
}