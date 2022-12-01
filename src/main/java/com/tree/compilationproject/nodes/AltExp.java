package com.tree.compilationproject.nodes;

import java.util.ArrayList;

public class AltExp extends Exp{
	private IfSign ifSign;
	private ConditionExp condition;
	private AffExp ifStatement;
	private ThenSign thenSign;
	private ElseSign elseSign;
	private AffExp elseStatement;
	
	public AltExp(){
		setExpanded(true);
		ArrayList<Exp> childrens = new ArrayList<>();
	    setChildrens(childrens);
	}
	
	public AltExp(IfSign ifSign,ConditionExp condition,AffExp ifStatement, ThenSign thenSign,ElseSign elseSign,AffExp elseStatement) {
		this.ifSign  = ifSign;
		this.condition = condition;
		this.ifStatement = ifStatement;
		this.thenSign = thenSign;
		this.elseSign  = elseSign;
		this.elseStatement = elseStatement;
		setExpanded(true);
	    ArrayList<Exp> childrens = new ArrayList<>();
	    childrens.add(this.ifSign);
        childrens.add(this.condition);
        childrens.add(this.ifStatement);
        childrens.add(this.thenSign);
        childrens.add(this.elseSign);
        childrens.add(this.elseStatement);
	    setChildrens(childrens);
	}

	@Override
	public String generateCodeCible() {
		return   this.condition.generateCodeCible()
				+ this.ifStatement.generateCodeCible() + "JMP EndIF" + this.elseSign.generateCodeCible() 
				+ this.elseStatement.generateCodeCible() + "EndIF:";
	}
	
	public String toString() {
		return "Alt";
	}
	
	public IfSign getIfSign() {
        return ifSign;
    }

    public void setIfSign(IfSign ifSign) {
        this.ifSign = ifSign;
    }

    public ConditionExp getCondition() {
        return condition;
    }

    public void setCondition(ConditionExp condition) {
        this.condition = condition;
    }
    
	public AffExp getIfStatement() {
        return ifStatement;
    }

    public void setIfStatement(AffExp statement) {
        this.ifStatement = statement;
    }
    public ThenSign getThenSign() {
        return thenSign;
    }

    public void setThenSign(ThenSign thenSign) {
        this.thenSign = thenSign;
    }
    public ElseSign getElseSign() {
        return elseSign;
    }

    public void setElseSign(ElseSign elseSign) {
        this.elseSign = elseSign;
    }
    
	public AffExp getElseStatement() {
        return elseStatement;
    }

	public void setElseStatement(AffExp statement) {
	    this.elseStatement = statement;
	}

}
