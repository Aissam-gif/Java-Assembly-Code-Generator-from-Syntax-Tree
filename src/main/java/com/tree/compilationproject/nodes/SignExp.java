package com.tree.compilationproject.nodes;

public class SignExp extends Exp{
    private String sign = "";

    public SignExp() {
    }

    public SignExp(String sign) {
        this.sign = sign;
        setExpanded(false);
    }

    @Override
    public String generateCodeCible() {
        switch (sign) {
            case "+": return "ADD \n";
            case "-": return "SUB \n";
            case "*": return "MUL \n";
            case "/": return "DIV \n";
        }
        return "";
    }

    @Override
    public String toString() {
        if (sign.equals("")) return "SignOp";

        return sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
