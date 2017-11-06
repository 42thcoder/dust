package com.hackerzhang.dust.ast;

import com.hackerzhang.dust.Token;

import java.util.ArrayList;
import java.util.Iterator;

public class ASTLeaf extends ASTree {
    // why static????
    private static ArrayList<ASTree> empty = new ArrayList<>();

    public Token getToken() {
        return token;
    }

    private Token token;

    public ASTLeaf(Token token) {
        this.token = token;
    }

    @Override
    public int numChildren() {
        return 0;
    }

    @Override
    public Iterator<ASTree> children() {
        return empty.iterator();
    }

    @Override
    public String location() {
        return "at line " + token.getLineNumber();
    }

    @Override
    public ASTree child(int i) {
        throw new IndexOutOfBoundsException();
    }

    @Override
    public String toString() {
        return token.getText();
    }
}
