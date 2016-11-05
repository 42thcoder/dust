package com.hackerzhang.dust;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public static String regexPat
            = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<>();
    private LineNumberReader reader;
    private boolean hasMore;

    public Lexer(Reader r) {
        this.reader = new LineNumberReader(r);
        this.hasMore = true;
    }

    public Token read() throws ParseException {
        if (fillQueue(0))
            return queue.remove(0);
        else
            return Token.EOF;
    }

    public Token peek(int i) throws ParseException {
        if (fillQueue(i))
            return queue.get(i);
        else
            return Token.EOF;
    }

    private boolean fillQueue(int i) throws ParseException {
        while (i >= queue.size()) {
            if (hasMore)
                readLine();
            else
                return false;
        }

        return true;
    }

    private void readLine() throws ParseException {
        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }

        if (line == null) {
            hasMore = false;
            return;
        }

        int lineNumber = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        int startPos = 0;
        int endPos = line.length();

        while (startPos < endPos) {
            matcher.region(startPos, endPos);

            if (matcher.lookingAt()) {
                addToken(lineNumber, matcher);
                endPos = matcher.end();
            } else
                throw new ParseException("bad token at line: " + lineNumber);
        }

        queue.add(new IdToken(lineNumber, Token.EOL));
    }

    private void addToken(int lineNumber, Matcher matcher) {
        String m = matcher.group(1);

        if (m != null && matcher.group(2) == null) {
            Token token;

            if (matcher.group(3) != null)
                token = new NumberToken(lineNumber, Integer.parseInt(m));
            else if (matcher.group(4) != null)
                token = new StrToken(lineNumber, toStringLiteral(m));
            else
                token = new IdToken(lineNumber, m);

            queue.add(token);

        }
    }

    //    Have no idea what fxxk this is? SB truly.
    protected String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = s.charAt(i + 1);
                if (c2 == '"' || c2 == '\\')
                    c = s.charAt(++i);
                else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }


    protected static class StrToken extends Token {
        private String literal;

        public StrToken(int line, String str) {
            super(line);
            literal = str;
        }

        public StrToken(int line) {
            super(line);
        }

        @Override
        public boolean isString() {
            return true;
        }

        @Override
        public String getText() {
            return literal;
        }
    }

    protected static class NumberToken extends Token {
        private int value;

        public NumberToken(int line, int v) {
            super(line);
            value = v;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        @Override
        public int getNumber() {
            return value;
        }
    }

    protected static class IdToken extends Token {
        private String text;

        public IdToken(int line, String id) {
            super(line);
            text = id;
        }

        @Override
        public boolean isIdentifier() {
            return true;
        }

        @Override
        public String getText() {
            return text;
        }
    }
}
