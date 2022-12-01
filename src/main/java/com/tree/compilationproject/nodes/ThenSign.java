package com.tree.compilationproject.nodes;

public class ThenSign extends Exp{
	  String value;

	    public ThenSign() {
	        this.value = "";
	        setExpanded(false);
	    }

	    @Override
	    public String generateCodeCible() {
	        return "Then :\n";
	    }

	    @Override
	    public String toString() {
	        return "Then";
	    }

	    public String getValue() {
	        return value;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }


}
