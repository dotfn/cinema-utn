package com.grupo2.cinemautn.persistence;
import com.grupo2.cinemautn.models.usuarios.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;

public class GestoraUsuariosJSON {

    //  SERIALIZAR
    public void listaToArchivo(ArrayList<Usuario> lista, String nombreArchivo) {
        OperacionesLectoEscritura.grabar (nombreArchivo, serializarLista(lista));

    }

    public JSONObject serializar(Usuario u) {
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


    public JSONArray serializarLista(ArrayList<Usuario> lista) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray();
            for (Usuario u: lista) {
                jsonArray.put(serializar(u)); // agrega el JSONObject al JSONArray
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // DESERIALIZAR

    public ArrayList<Usuario> archivoALista(String nombreArchivo) {
        JSONTokener tokener = OperacionesLectoEscritura.leer(nombreArchivo);
        ArrayList<Usuario> lista = null;
        try {
            lista = deserializarLista(new JSONArray(tokener));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario deserializar (JSONObject jsonObject) {
        Usuario u = new Usuario();
        try {
            // TODO: completar con los atributos de Usuario
            //u.setNombre(jsonObject.getString("nombre"));
            //u.setEdad(jsonObject.getInt("edad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }

    public ArrayList<Usuario> deserializarLista (JSONArray jsonArray) {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Usuario u = deserializar(jsonArray.getJSONObject(i));
                lista.add(u);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }
}