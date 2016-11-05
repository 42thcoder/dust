package com.hackerzhang.dust;

public abstract class Token {
    private int lineNumber;
    public static final Token EOF = new Token(-1){};
    public static final String EOL = "\\n";


    public Token(int line) {
        lineNumber = line;
    }

    @Override
    public String toString() {
        return "Token";
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public boolean isNumber() {
        return false;
    }

    public boolean isString(){
        return false;
    }

    public boolean isIdentifier(){
        return false;
    }

    public int getNumber() {
        throw new DustException("not number token");
    }

    public String getText() {
        return "";
    }
}
