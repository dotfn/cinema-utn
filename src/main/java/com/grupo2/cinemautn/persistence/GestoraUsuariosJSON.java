package com.grupo2.cinemautn.persistence;
import com.grupo2.cinemautn.models.usuarios.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;

public class GestoraUsuariosJSON {

    //  SERIALIZAR
    public void listaToArchivo(ArrayList<Usuarios> lista, String nombreArchivo) {
        OperacionesLectoEscritura.grabar (nombreArchivo, serializarLista(lista));

    }

    public JSONObject serializar(Usuarios u) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            //TODO: completar con los atributos de Usuario
            //jsonObject.put("nombre", u.getNombre());
            //jsonObject.put("edad", u.getEdad());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


    public JSONArray serializarLista(ArrayList<Usuarios> lista) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Usuarios u: lista) {
                jsonArray.put(serializar(u)); // agrega el JSONObject al JSONArray
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // DESERIALIZAR

    public ArrayList<Usuarios> archivoALista(String nombreArchivo) {
        JSONTokener tokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Usuarios> lista = null;
        try {
            lista = deserializarLista(new JSONArray(tokener));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuarios deserializar (JSONObject jsonObject) {
        Usuarios u = new Usuarios();
        try {
            // TODO: completar con los atributos de Usuario
            //u.setNombre(jsonObject.getString("nombre"));
            //u.setEdad(jsonObject.getInt("edad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }

    public ArrayList<Usuarios> deserializarLista (JSONArray jsonArray) {
        ArrayList<Usuarios> lista = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Usuarios u = deserializar(jsonArray.getJSONObject(i));
                lista.add(u);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}