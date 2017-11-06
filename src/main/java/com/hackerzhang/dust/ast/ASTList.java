package com.hackerzhang.dust.ast;

import java.util.Iterator;
import java.util.List;

public class ASTList extends ASTree{
    private List<ASTree> children;

    public ASTList(List<ASTree> children) {
        this.children = children;
    }

    @Override
    public int numChildren() {
        return children.size();
    }

    @Override
    public Iterator<ASTree> children() {
        return children.iterator();
    }

    @Override
    public String location() {
        for (ASTree tree : children) {
            String location = tree.location();

            if(location != null) {
                return location;
            }
        }

        return null;
    }

    @Override
    public ASTree child(int i) {
        return children.get(i);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("(");
        for (ASTree tree : children) {
            builder.append(tree.toString());
        }
        builder.append(")");

        return builder.toString();
    }
}
