package com.hackerzhang.dust.ast;

import com.hackerzhang.dust.Token;

public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token token) {
        super(token);
    }

    public String value() {
        return getToken().getText();
    }
}
