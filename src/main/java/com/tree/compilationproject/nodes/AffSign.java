package com.tree.compilationproject.nodes;

public class AffSign extends Exp  {
    String value;

    public AffSign() {
        this.value = "";
        setExpanded(false);
    }

    @Override
    public String generateCodeCible() {
        return "=\n";
    }

    @Override
    public String toString() {
        return ":=";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
