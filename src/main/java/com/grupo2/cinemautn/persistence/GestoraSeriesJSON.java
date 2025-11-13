package com.grupo2.cinemautn.persistence;

import com.grupo2.cinemautn.models.contenido.Serie;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.Collection;

public class GestoraSeriesJSON {

    private static final String DEFAULT_FILE = "series.json";

    //  SERIALIZAR
    public <C extends Collection<Serie>> void listaToArchivo(C lista) {
        ArrayList<Serie> arrayListSeries = new ArrayList<>(lista);
        OperacionesLectoEscritura.grabar(DEFAULT_FILE, serializarLista(arrayListSeries));
    }

    public JSONObject serializar(Serie s) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            //TODO: completar con los atributos de Serie
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

    public ArrayList<Serie> archivoALista() {
        JSONTokener tokener = OperacionesLectoEscritura.leer(DEFAULT_FILE);
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