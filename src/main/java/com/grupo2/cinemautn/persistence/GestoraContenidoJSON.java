package com.grupo2.cinemautn.persistence;

import com.grupo2.cinemautn.models.contenido.*;
import com.grupo2.cinemautn.models.resena.Resena;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collection;

public class GestoraContenidoJSON {

    private static final String DEFAULT_FILE = "data/contenido.json";

    // ---------------------------
    // SERIALIZAR
    // ---------------------------
    public <C extends Collection<Contenido>> void listaToArchivo(C lista) {
        OperacionesLectoEscritura.grabar(
                DEFAULT_FILE,
                serializarLista(new ArrayList<>(lista))
        );
    }

    private JSONObject serializarContenidoComun(Contenido c) {
        JSONObject json = new JSONObject();

        json.put("id", c.getId());
        json.put("titulo", c.getTitulo());
        json.put("genero", c.getGenero() != null ? c.getGenero().name() : JSONObject.NULL);
        json.put("anio", c.getAnio());
        json.put("director", c.getDirector());
        json.put("estado", c.isEstado());
        json.put("imagenPortada", c.getImagenPortada());

        return json;
    }

    public JSONObject serializar(Contenido c) {
        JSONObject json = serializarContenidoComun(c);

        if (c instanceof Pelicula p) {
            json.put("tipo", TipoContenido.PELICULA.name());
            json.put("duracion", p.getDuracion());
        } else if (c instanceof Serie s) {
            json.put("tipo", TipoContenido.SERIE.name());
            json.put("temporadas", s.getTemporadas());
            json.put("episodios", s.getEpisodios());
        }

        JSONArray arr = new JSONArray();
        if (c.getResenas() != null) {
            for (Resena r : c.getResenas()) {
                JSONObject jr = new JSONObject();
                jr.put("id", r.getId());
                jr.put("idUsuario", r.getIdUsuario());
                jr.put("estrellas", r.getEstrellas());
                jr.put("estado", r.isEstado());
                arr.put(jr);
            }
        }
        json.put("resenas", arr);

        return json;
    }

    public JSONArray serializarLista(ArrayList<Contenido> lista) {
        JSONArray arr = new JSONArray();
        for (Contenido c : lista) arr.put(serializar(c));
        return arr;
    }

    // ---------------------------
    // DESERIALIZAR
    // ---------------------------
    public ArrayList<Contenido> archivoALista() {
        JSONTokener tokener = OperacionesLectoEscritura.leer(DEFAULT_FILE);
        if (tokener == null) return new ArrayList<>();
        return deserializarLista(new JSONArray(tokener));
    }

    private <T extends Enum<T>> T safeEnum(String value, Class<T> type) {
        try { return Enum.valueOf(type, value); }
        catch (Exception e) { return null; }
    }

    public Contenido deserializar(JSONObject json) {

        String tipoStr = json.optString("tipo", null);
        TipoContenido tipo = safeEnum(tipoStr, TipoContenido.class);

        String titulo = json.optString("titulo", null);
        Genero genero = safeEnum(json.optString("genero", null), Genero.class);
        int anio = json.optInt("anio", 0);
        String director = json.optString("director", null);
        String imagen = json.optString("imagenPortada", null);
        boolean estado = json.optBoolean("estado", true);

        Contenido c = null;

        if (tipo == TipoContenido.PELICULA) {
            double duracion = json.optDouble("duracion", 0);
            c = new Pelicula(titulo, genero, anio, director, imagen, new ArrayList<>(), duracion);
        } else if (tipo == TipoContenido.SERIE) {
            int temporadas = json.optInt("temporadas", 0);
            int episodios = json.optInt("episodios", 0);
            c = new Serie(titulo, genero, anio, director, imagen, new ArrayList<>(), temporadas, episodios);
        } else {
            return null; // tipo desconocido
        }

        c.setId(json.optInt("id", 0));
        c.setEstado(estado);

        // resenas
        JSONArray arr = json.optJSONArray("resenas");
        c.setResenas(new ArrayList<>());
        if (arr != null) {
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jr = arr.getJSONObject(i);
                Resena r = new Resena();
                r.setId(jr.optInt("id", 0));
                r.setIdUsuario(jr.optInt("idUsuario", 0));
                r.setEstrellas(jr.optInt("estrellas", 0));
                r.setEstado(jr.optBoolean("estado", true));
                c.agregarResena(r);
            }
        }

        return c;
    }

    public ArrayList<Contenido> deserializarLista(JSONArray arr) {
        ArrayList<Contenido> lista = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            Contenido c = deserializar(arr.getJSONObject(i));
            if (c != null) lista.add(c);
        }
        return lista;
    }
}
