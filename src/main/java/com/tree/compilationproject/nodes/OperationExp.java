package com.tree.compilationproject.nodes;

import java.util.ArrayList;

public class OperationExp extends Exp  {
    private SignExp signOp;
    private Exp leftExp;
    private Exp rightExp;

    public OperationExp() {
        setExpanded(true);
    }

    public OperationExp(SignExp signOp, Exp leftExp, Exp rightExp) {
        this.signOp = signOp;
        this.leftExp = leftExp;
        this.rightExp = rightExp;
        setExpanded(true);
        ArrayList<Exp> childrens = new ArrayList<>();
        childrens.add(this.leftExp);
        childrens.add(this.signOp);
        childrens.add(this.rightExp);
        setChildrens(childrens);
    }

    @Override
    public String generateCodeCible() {
        codeCible = leftExp.generateCodeCible()+
                rightExp.generateCodeCible()+
                signOp.generateCodeCible();
        return codeCible;
    }

    @Override
    public String toString() {
        return "Exp";
    }

    public SignExp getSignOp() {
        return signOp;
    }

    public void setSignOp(SignExp signOp) {
        this.signOp = signOp;
    }

    public Exp getLeftExp() {
        return leftExp;
    }

    public void setLeftExp(Exp leftExp) {
        this.leftExp = leftExp;
    }

    public Exp getRightExp() {
        return rightExp;
    }

    public void setRightExp(Exp rightExp) {
        this.rightExp = rightExp;
    }
}
