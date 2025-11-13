package com.grupo2.cinemautn.persistence;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.models.usuarios.Rol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.Collection;

public class GestoraUsuariosJSON {

    private static final String DEFAULT_FILE = "data/usuarios.json";

    // Método genérico para aceptar cualquier colección de usuarios
    public <C extends Collection<Usuario>> void listaToArchivo(C lista) {
        ArrayList<Usuario> arrayListUsuarios = new ArrayList<>(lista);
        OperacionesLectoEscritura.grabar(DEFAULT_FILE, serializarLista(arrayListUsuarios));
    }

    public JSONObject serializar(Usuario u) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            // Guardar atributos esenciales
            jsonObject.put("id", u.getIdUsuario());
            jsonObject.put("nombre", u.getNombre());
            jsonObject.put("email", u.getEmail());
            jsonObject.put("contrasena", u.getContrasena());
            jsonObject.put("estado", u.isEstado());
            jsonObject.put("rol", u.getRol() != null ? u.getRol().name() : JSONObject.NULL);
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

    public ArrayList<Usuario> archivoALista() {
        JSONTokener tokener = OperacionesLectoEscritura.leer(DEFAULT_FILE);
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            if (tokener == null) return lista; // archivo no existe -> lista vacía
            lista = deserializarLista(new JSONArray(tokener));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario deserializar (JSONObject jsonObject) {
        Usuario u = new Usuario();
        try {
            if (jsonObject.has("id") && !jsonObject.isNull("id")) u.setIdUsuario(jsonObject.getInt("id"));
            if (jsonObject.has("nombre") && !jsonObject.isNull("nombre")) u.setNombre(jsonObject.getString("nombre"));
            if (jsonObject.has("email") && !jsonObject.isNull("email")) u.setEmail(jsonObject.getString("email"));
            if (jsonObject.has("contrasena") && !jsonObject.isNull("contrasena")) u.setContrasena(jsonObject.getString("contrasena"));
            if (jsonObject.has("estado") && !jsonObject.isNull("estado")) u.setEstado(jsonObject.getBoolean("estado"));
            if (jsonObject.has("rol") && !jsonObject.isNull("rol")) {
                try {
                    u.setRol(Rol.valueOf(jsonObject.getString("rol")));
                } catch (IllegalArgumentException e) {
                    // rol desconocido, dejar null
                }
            }
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