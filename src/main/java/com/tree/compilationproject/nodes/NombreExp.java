package com.tree.compilationproject.nodes;

public class NombreExp extends Exp {
    private String nombre = "";

    public NombreExp() {
        this.nombre = "0";
        setExpanded(false);
    }

    public NombreExp(String nombre) {
        this.nombre = nombre;
        setExpanded(false);
    }

    @Override
    public String generateCodeCible() {
        codeCible = "LOADC<"+nombre+"> \n";
        return codeCible;
    }

    @Override
    public String toString() {
        return String.valueOf(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
