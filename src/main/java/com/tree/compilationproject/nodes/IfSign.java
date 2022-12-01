package com.tree.compilationproject.nodes;

public class IfSign extends Exp {
	
	  String value;

	    public IfSign() {
	        this.value = "";
	        setExpanded(false);
	    }

	    @Override
	    public String generateCodeCible() {
	        return "If\n";
	    }

	    @Override
	    public String toString() {
	        return "if";
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }


}
