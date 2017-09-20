package com.company;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

class LexerTest {
    @org.junit.jupiter.api.Test
    void hasNext() {
        Lexer testLexer = new Lexer("this and that");
        for(int numberTokens=0; numberTokens<1; numberTokens++) {
            boolean testBool = testLexer.hasNext();
            System.out.println(testBool);
            Assertions.assertTrue(testBool);
        }
        Assertions.assertTrue(!testLexer.hasNext());
    }

    @org.junit.jupiter.api.Test
    void next() {
        Lexer testLexer = new Lexer("and that");
        LocationalToken testLocationalToken = testLexer.next();
        Assertions.assertEquals(testLocationalToken.getLocation(), 0);
        Assertions.assertEquals(testLocationalToken.getToken().getType(), Token.Type.AND);
    }

    @org.junit.jupiter.api.Test
    void nextValid() {
        Lexer testLexer = new Lexer("");
        Optional<LocationalToken> testLocationalToken = testLexer.nextValid(new Hashtable(), new Hashtable());
        Assertions.assertEquals(testLocationalToken, Optional.empty());
    }

}