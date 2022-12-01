package com.tree.compilationproject.nodes;

import java.util.ArrayList;

public class AffExp extends Exp{
    private IdentExp identExp;
    private AffSign affectationOp;
    private Exp rightExpression;
    private String cibleCode; 

    public AffExp() {
        setExpanded(true);
        ArrayList<Exp> childrens = new ArrayList<>();
        setChildrens(childrens);
    }

    public AffExp(IdentExp identExp, AffSign affectationOp, Exp rightExpression) {
        this.identExp = identExp;
        this.affectationOp = affectationOp;
        this.rightExpression = rightExpression;
        this.identExp.setParent(this);
        setExpanded(true);
        ArrayList<Exp> childrens = new ArrayList<>();
        childrens.add(this.identExp);
        childrens.add(this.affectationOp);
        childrens.add(this.rightExpression);
        setChildrens(childrens);
    }


    @Override
    public String generateCodeCible() {
        return this.rightExpression.generateCodeCible() + this.identExp.generateCodeCible();
    }

    @Override
    public String toString() {
        return "Aff";
    }

    public IdentExp getIdentExp() {
        return identExp;
    }

    public void setIdentExp(IdentExp identExp) {
        this.identExp = identExp;
    }

    public AffSign getAffectationOp() {
        return affectationOp;
    }

    public void setAffectationOp(AffSign affectationOp) {
        this.affectationOp = affectationOp;
    }

    public Exp getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(Exp rightExpression) {
        this.rightExpression = rightExpression;
    }
}
