package com.hackerzhang.dust.ast;

import java.util.List;

public class BinaryExpr extends ASTList {
    public BinaryExpr(List<ASTree> children) {
        super(children);
    }

    public ASTree left() {
        return child(0);
    }

    public String operator() {
        // 需要做转型
        return ((ASTLeaf)child(1)).getToken().getText();
    }

    public ASTree right() {
        return child(2);
    }
}

