package com.hackerzhang.dust;

import com.hackerzhang.dust.ast.ASTree;

public class DustException extends RuntimeException {

    public DustException(String message) {
        super(message);
    }

    public DustException(String message, ASTree tree) {
        super(message + " " + tree.location());
    }
}
