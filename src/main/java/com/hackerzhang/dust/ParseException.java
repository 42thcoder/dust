package com.hackerzhang.dust;

public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }

    public ParseException(Exception e) {
        super(e);
    }
}
