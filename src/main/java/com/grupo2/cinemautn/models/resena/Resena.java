package com.grupo2.cinemautn.models.resena;

public class Resena {
    //Atributos
    private static int contador = 0;
    private int id;
    private int idUsuario;
    private int estrellas;
    private StringBuilder cometario;
    private boolean estado;

    // Constructor
    public Resena() {
    }

    public Resena(int idUsuario, int estrellas, StringBuilder cometario) {
        contador ++;
        this.id = contador;
        this.idUsuario = idUsuario;
        this.estrellas = estrellas;
        this.cometario = cometario;
        this.estado = true;
    }

    // Getters y Setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getIdUsuario() {return idUsuario;}
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    public double getEstrellas() {return estrellas;}
    public void setEstrellas(int estrellas) {this.estrellas = estrellas;}

    public boolean isEstado() {return estado;}
    public void setEstado(boolean estado) {this.estado = estado;}
}


