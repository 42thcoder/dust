package com.hackerzhang.dust.ast;

import com.hackerzhang.dust.Token;

public class Name extends ASTLeaf {
    public Name(Token token) {
        super(token);
    }

    public String name() {
        return getToken().getText();
    }
}
