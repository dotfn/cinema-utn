package com.grupo2.cinemautn.models.resena;

import java.util.Objects;

public class Resena {
    //Atributos
    private static int contador = 0;
    private int id;
    private int idUsuario;
    private int estrellas;
    private String comentario; // ahora guardamos el comentario como String
    private boolean estado;

    // Constructor vacío
    public Resena() {
    }

    // Constructor con comentario
    public Resena(int idUsuario, int estrellas, String comentario) {
        contador ++;
        this.id = contador;
        this.idUsuario = idUsuario;
        this.estrellas = estrellas;
        this.comentario = comentario;
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

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Resena resena = (Resena) o;
        return id == resena.id && idUsuario == resena.idUsuario && estrellas == resena.estrellas && estado == resena.estado && Objects.equals(comentario, resena.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUsuario, estrellas, comentario, estado);
    }

    @Override
    public String
    toString() {
        return "Resena{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", estrellas=" + estrellas +
                ", comentario='" + comentario + '\'' +
                ", estado=" + estado +
                '}';
    }
}
