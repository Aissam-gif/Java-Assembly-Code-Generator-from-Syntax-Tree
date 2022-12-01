package com.tree.compilationproject.nodes;

public class ElseSign extends Exp{
	String value;

    public ElseSign() {
        this.value = "";
        setExpanded(false);
    }

    @Override
    public String generateCodeCible() {
        return "\nElse:\n";
    }

    @Override
    public String toString() {
        return "else";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
