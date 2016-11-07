package com.hackerzhang.dust;

public class ParseException extends Exception {
    public ParseException(Token token) {
        this("", token);
    }

    public ParseException(String message) {
        super(message);
    }

    public ParseException(Exception e) {
        super(e);
    }

    public ParseException(String message, Token token) {
        super("syntax error around " + location(token) + ".\n" + message);
    }

    //TODO why static
    private static String location(Token token){
        if(token == Token.EOF)
            return "the last line";
        else
            return "\"" + token.getText() + "\" at line " + token.getLineNumber();
    }
}
