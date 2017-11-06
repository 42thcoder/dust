package com.hackerzhang.dust.ast;

import com.hackerzhang.dust.Token;

public class Number extends ASTLeaf {
    public Number(Token token) {
        super(token);
    }

    public int value() {
        return getToken().getNumber();
    }
}
