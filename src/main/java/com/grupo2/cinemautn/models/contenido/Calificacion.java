package com.grupo2.cinemautn.models.contenido;

public class Calificacion {
    //Atributos
    private int id;
    private int idUsuario;
    private int idContenido;
    private double estrellas;
    private boolean estado; // por ejemplo: activa/inactiva

    // Constructor
    public Calificacion(int id, int idUsuario, int idContenido, double estrellas, boolean estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idContenido = idContenido;
        this.estrellas = estrellas;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getIdUsuario() {return idUsuario;}
    public void setIdUsuario(int idUsuario) {this.idUsuario = idUsuario;}

    public int getIdContenido() {return idContenido;}
    public void setIdContenido(int idContenido) {this.idContenido = idContenido;}

    public double getEstrellas() {return estrellas;}
    public void setEstrellas(double estrellas) {this.estrellas = estrellas;}

    public boolean isEstado() {return estado;}
    public void setEstado(boolean estado) {this.estado = estado;}
}
