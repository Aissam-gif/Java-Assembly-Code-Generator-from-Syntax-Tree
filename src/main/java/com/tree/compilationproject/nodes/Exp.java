package com.tree.compilationproject.nodes;

import java.util.ArrayList;

abstract public class Exp {
    private Exp parent = null;
    protected ArrayList<Exp> childrens = null;
    protected String codeCible;
    protected boolean isExpanded = false;
    protected boolean isExpUsed;

    public void setParent(Exp parent) {
        this.parent = parent;
    }

    public Exp getParent() {
        return parent;
    }

    public ArrayList<Exp> getChildrens() {
        return childrens;
    }

    public void setChildrens(ArrayList<Exp> childrens) {
        this.childrens = childrens;
    }

    public abstract String generateCodeCible();

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setExpUsed(boolean expUsed) {
        isExpUsed = expUsed;
    }

    public boolean isExpUsed() {
        return isExpUsed;
    }
}
