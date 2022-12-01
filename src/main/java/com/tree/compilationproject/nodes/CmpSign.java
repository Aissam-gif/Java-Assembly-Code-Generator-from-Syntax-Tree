package com.tree.compilationproject.nodes;

public class CmpSign extends Exp{

	private String sign = "";

    public CmpSign() {
    }

    public CmpSign(String sign) {
        this.sign = sign;
        setExpanded(false);
    }

    @Override
    public String generateCodeCible() {
        switch (sign) {
            case "==": return "CMPNE Else \n";
            case "!=": return "CMPEQ Else \n";
            case ">=": return "CMPLT Else \n";
            case ">": return "CMPLE Else \n";
            case "<=": return "CMPGT Else \n";
            case "<": return "CMPGE Else \n";
        }
        return "";
    }

    @Override
    public String toString() {
        if (sign.equals("")) return "SignCondition";

        return sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
