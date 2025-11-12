package com.grupo2.cinemautn.persistence;
import com.grupo2.cinemautn.models.contenido.Pelicula;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;

public class GestoraPeliculasJSON {



    //  SERIALIZAR
    public void listaToArchivo(ArrayList<Pelicula> lista, String nombreArchivo) {
        OperacionesLectoEscritura.grabar (nombreArchivo, serializarLista(lista));

    }

    public JSONObject serializar(Pelicula p) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            //TODO: completar con los atributos de Pelicula
            //jsonObject.put("nombre", p.getNombre());
            //jsonObject.put("edad", p.getEdad());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONArray serializarLista(ArrayList<Pelicula> lista) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Pelicula p: lista) {
                jsonArray.put(serializar(p)); // agrega el JSONObject al JSONArray
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // DESERIALIZAR

    public ArrayList<Pelicula> archivoALista(String nombreArchivo) {
        JSONTokener tokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Pelicula> lista = null;
        try {
            lista = deserializarLista(new JSONArray(tokener));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Pelicula deserializar (JSONObject jsonObject) {
        Pelicula p = new Pelicula();
        try {
            // TODO: completar con los atributos de Usuario
            //p.setNombre(jsonObject.getString("nombre"));
            //p.setEdad(jsonObject.getInt("edad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return p;
    }

    public ArrayList<Pelicula> deserializarLista (JSONArray jsonArray) {
        ArrayList<Pelicula> lista = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Pelicula p = deserializar(jsonArray.getJSONObject(i));
                lista.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}