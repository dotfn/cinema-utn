package com.grupo2.cinemautn.interfaces;

import java.util.List;

public interface IGestionable<T> {
    void crear(T c);
    T leer(int id);
    void actualizar(T c);
    void eliminar(int id);
    List<T> listar();
}