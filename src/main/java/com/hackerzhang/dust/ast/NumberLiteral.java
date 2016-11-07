package com.hackerzhang.dust.ast;

import com.hackerzhang.dust.Token;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token token) {
        super(token);
    }

    public int value() {
        return getToken().getNumber();
    }
}
