package com.grupo2.cinemautn.persistence;
import com.grupo2.cinemautn.models.usuarios.Usuario;
import com.grupo2.cinemautn.models.usuarios.Rol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.List;

public class GestoraUsuariosJSON {

    private static final String DEFAULT_FILE = "usuarios.json";

    //  SERIALIZAR
    public void listaToArchivo(ArrayList<Usuario> lista) {
        OperacionesLectoEscritura.grabar (DEFAULT_FILE, serializarLista(lista));

    }

    public JSONObject serializar(Usuario u) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject();
            jsonObject.put("id", u.getIdUsuario());
            jsonObject.put("nombre", u.getNombre());
            jsonObject.put("correo", u.getEmail());
            jsonObject.put("contrasena", u.getContrasena());
            jsonObject.put("estado", u.isEstado());
            jsonObject.put("rol", u.getRol() != null ? u.getRol().name() : JSONObject.NULL);

            // favoritos: lista de ids (si hay)
            JSONArray favArray = new JSONArray();
            if (u.getListaFavoritos() != null && u.getListaFavoritos().getFavoritos() != null) {
                for (var c : u.getListaFavoritos().getFavoritos()) {
                    favArray.put(c.getId());
                }
            }
            jsonObject.put("favoritos", favArray);

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
            if (tokener == null) return lista;
            lista = deserializarLista(new JSONArray(tokener));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario deserializar (JSONObject jsonObject) {
        Usuario u = new Usuario();
        try {
            if (jsonObject.has("id")) u.setIdUsuario(jsonObject.getInt("id"));
            if (jsonObject.has("nombre")) u.setNombre(jsonObject.getString("nombre"));
            if (jsonObject.has("correo")) u.setEmail(jsonObject.getString("correo"));
            if (jsonObject.has("contrasena")) u.setContrasena(jsonObject.getString("contrasena"));
            if (jsonObject.has("estado")) u.setEstado(jsonObject.getBoolean("estado"));
            if (jsonObject.has("rol") && !jsonObject.isNull("rol")) {
                try {
                    u.setRol(Rol.valueOf(jsonObject.getString("rol")));
                } catch (IllegalArgumentException ignored) {}
            }

            // favoritos: lista de ids (deserializamos como vacío; necesitará resolución externa a objetos Contenido)
            if (jsonObject.has("favoritos")) {
                // dejamos la lista vacía; la resolución a objetos Contenido puede hacerse desde UsuarioService si se dispone de ContenidoService
                // (aquí sólo preservamos los ids en el JSON; no resolvemos objetos para no acoplar gestora a ContenidoService)
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