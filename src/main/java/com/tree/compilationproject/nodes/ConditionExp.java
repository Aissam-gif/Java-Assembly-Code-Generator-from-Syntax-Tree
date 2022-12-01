package com.tree.compilationproject.nodes;

import java.util.ArrayList;

public class ConditionExp extends Exp{
	private Exp rightExpression;
	private Exp leftExpression;
	private CmpSign cmpSign;

	public ConditionExp(){
		setExpanded(true);
		ArrayList<Exp> childrens = new ArrayList<>();
	    setChildrens(childrens);
	}
	
	public ConditionExp(Exp rightExpression,Exp leftExpression,CmpSign cmpSign ) {
		this.rightExpression  = rightExpression;
		this.leftExpression = leftExpression;
		this.cmpSign = cmpSign;
		setExpanded(true);
	    ArrayList<Exp> childrens = new ArrayList<>();
	    childrens.add(this.rightExpression);
        childrens.add(this.leftExpression);
        childrens.add(this.cmpSign);
	    setChildrens(childrens);
	}

	@Override
	public String generateCodeCible() {
		return this.leftExpression.generateCodeCible() + this.rightExpression.generateCodeCible()
				+ this.cmpSign.generateCodeCible();
	}
	
	public String toString() {
		return "Condition";
	}
	
	public Exp getRightExpression() {
        return rightExpression;
    }

    public void setRightExpression(Exp rightExpression) {
        this.rightExpression = rightExpression;
    }
    
	public Exp getLeftExpression() {
        return leftExpression;
    }

	public void setLeftExpression(Exp leftExpression) {
	    this.leftExpression = leftExpression;
	}
	public CmpSign getCmpSign() {
        return cmpSign;
    }

    public void setCmpSign(CmpSign cmpSign) {
        this.cmpSign = cmpSign;
    }

}
