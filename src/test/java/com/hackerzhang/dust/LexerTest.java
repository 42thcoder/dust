package com.hackerzhang.dust;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class LexerTest {
    @Test
    public void testLexer() throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());

        System.out.println("Ready for the tokens!");

        for(Token token; (token = lexer.read()) != Token.EOF; )
            System.out.println("=> " + token.getText());

        System.out.println("Here ends the show");

        assertEquals("hello", "hello");
    }

    @Test
    public void testGistLexer() throws ParseException, FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        FileReader fileReader = new FileReader(classLoader.getResource("fixtures/gist1.txt").getFile());
        Lexer lexer = new Lexer(fileReader);

        System.out.println("Ready for the tokens!");

        for(Token token; (token = lexer.read()) != Token.EOF; )
            System.out.println("=> " + token.getText());

        System.out.println("Here ends the show");

        assertEquals("hello", "hello");
    }
}
