package com.grupo2.cinemautn.persistence;

import com.grupo2.cinemautn.models.contenido.Serie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;

public class GestoraSeriesJSON {

    //  SERIALIZAR
    public void listaToArchivo(ArrayList<Serie> lista, String nombreArchivo) {
        OperacionesLectoEscritura.grabar (nombreArchivo, serializarLista(lista));

    }

    public JSONObject serializar(Serie s) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            //TODO: completar con los atributos de Pelicula
            //jsonObject.put("nombre", s.getNombre());
            //jsonObject.put("edad", s.getEdad());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONArray serializarLista(ArrayList<Serie> lista) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Serie s: lista) {
                jsonArray.put(serializar(s)); // agrega el JSONObject al JSONArray
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // DESERIALIZAR

    public ArrayList<Serie> archivoALista(String nombreArchivo) {
        JSONTokener tokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Serie> lista = null;
        try {
            lista = deserializarLista(new JSONArray(tokener));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Serie deserializar (JSONObject jsonObject) {
        Serie s = new Serie();
        try {
            // TODO: completar con los atributos de Usuario
            //s.setNombre(jsonObject.getString("nombre"));
            //s.setEdad(jsonObject.getInt("edad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return s;
    }

    public ArrayList<Serie> deserializarLista (JSONArray jsonArray) {
        ArrayList<Serie> lista = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Serie s = deserializar(jsonArray.getJSONObject(i));
                lista.add(s);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}