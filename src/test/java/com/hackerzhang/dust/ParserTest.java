package com.hackerzhang.dust;

import com.hackerzhang.dust.ast.ASTree;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParserTest {
    @Test
    public void testParserRunner() throws ParseException {
        Lexer lexer = new Lexer(new CodeDialog());
        BasicParser basicParser = new BasicParser();

        while(lexer.peek(0) != Token.EOF) {
            ASTree asTree = basicParser.parse(lexer);
            System.out.println("=> " + asTree.toString());
        }
    }

    @Test
    public void testGistParser() throws ParseException, FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        FileReader fileReader = new FileReader(classLoader.getResource("fixtures/gist2.txt").getFile());
        Lexer lexer = new Lexer(fileReader);
        BasicParser basicParser = new BasicParser();

        while(lexer.peek(0) != Token.EOF) {
            ASTree asTree = basicParser.parse(lexer);
            System.out.println("=> " + asTree.toString());
        }
    }
}
