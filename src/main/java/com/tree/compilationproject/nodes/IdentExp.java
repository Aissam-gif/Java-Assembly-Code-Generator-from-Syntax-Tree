package com.tree.compilationproject.nodes;

public class IdentExp extends Exp {
    String name = "";
    String cible;
    private Exp parent = null;

    public IdentExp() {
        setExpanded(false);
    }

    public IdentExp(String name) {
        this.name = name;
        setExpanded(false);
    }

    @Override
    public String generateCodeCible() {
        Exp parent = getParent();
        if (parent != null && parent.getClass().getSimpleName().equals("AffExp")) {
            this.cible = "STORE<adr("+name+")> \n";
        } else {
            this.cible = "LOAD<adr("+name+")> \n";
        }
        return this.cible;
    }

    @Override
    public String toString() {
        if (name.equals("")) return "Ident";
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
