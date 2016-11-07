package com.hackerzhang.dust;

import com.hackerzhang.dust.ast.ASTree;
import org.junit.Test;

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
}
