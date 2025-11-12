package com.grupo2.cinemautn.interfaces;

import java.util.List;

public interface ABMCL<T> {
    void alta(T c);
    void baja(int id);
    void modificacion(T c);
    T consulta(int id);
    List<T> listar();
}